package demo.springboot.controller;

import demo.springboot.common.domain.Result;
import demo.springboot.common.domain.ResultCode;
import demo.springboot.common.exception.ParameterErrorException;
import demo.springboot.domain.Product;
import demo.springboot.service.IProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * 抢购(测试 单独乐观锁 和 mq异步处理)
 *
 * @author LILUO
 * @date 2020/04/09
 */
@Slf4j
@AllArgsConstructor
@RestController
public class PurchaseController {

    private IProductService productService;

    private RabbitTemplate rabbitTemplate;

    /**
     * 新增产品
     *
     * @param productName
     * @param amount
     * @return
     */
    @PostMapping("/product/add")
    public Result addProduct(String productName, Integer amount){

        Product product = new Product();
        product.setProductName(productName);
        product.setAmount(amount);
        return Result.status(productService.save(product));
    }

    /**
     * 查看产品列表
     *
     * @return
     */
    @GetMapping("/product/list")
    public Result<List<Product>> list(){
        return Result.data(productService.list());
    }

    /**
     * 抢购
     *
     * @param id
     * @return
     */
    @PostMapping("/purchase/{id}")
    public Result purchase(@PathVariable Long id){
        return Result.status(productService.purchase(id));
    }

    /**
     * 抢购走mq
     *
     * @param id
     * @return
     */
    @PostMapping("/purchase2/{id}")
    public Result purchase2(@PathVariable Long id){

        Product product = productService.getById(id);
        if (Objects.isNull(product) || product.getAmount() == 0){
            throw new ParameterErrorException(ResultCode.AMOUNT_NOT_ENOUGH);
        }

        rabbitTemplate.convertAndSend("amq.direct", "product_purchase", String.valueOf(id));

        return Result.success();
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("product_purchase"),
            exchange = @Exchange(value = "amq.direct", type = ExchangeTypes.DIRECT),
            key = "product_purchase"
    ))
    public void productPurchase(Message message) {

        //log.info("product_purchase data: {}", message.toString());

        boolean flag;
        try{
            flag = productService.purchase(Long.valueOf(new String(message.getBody())));
        }catch (Exception e){
            flag = false;
        }
        if (!flag){
            log.error("product_purchase fail, no product");
        }else{
            log.info("product_purchase success");
        }
    }
}
