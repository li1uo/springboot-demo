package demo.springboot.controller;

import demo.springboot.common.domain.Result;
import demo.springboot.domain.EntrustOrder;
import demo.springboot.service.IEntrustOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Random;

/**
 * 订单撮合
 *
 * @author LILUO
 * @date 2020/06/23
 */
@Api(value = "订单", tags = "订单")
@RequestMapping("/order")
@AllArgsConstructor
@Slf4j
@RestController
public class OrderMatchController {

    /**时间格式化**/
    public static final String DATETIME_FORMAT = "yyyyMMddHHmmss";

    private IEntrustOrderService entrustOrderService;

    @ApiOperation(value = "创建订单")
    @PostMapping("/create")
    public Result createOrder(){
        // 随机创建买单卖单
        int orderType = new Random().nextInt(2);
        // 随机用户id
        int userId = new Random().nextInt(100000);
        // 随机价格
        BigDecimal price = BigDecimal.valueOf(100).add(BigDecimal.valueOf(new Random().nextInt(20)));
        // 随机数量
        BigDecimal amount = BigDecimal.valueOf(new Random().nextDouble()).setScale(2, RoundingMode.DOWN);
        if (amount.compareTo(BigDecimal.ZERO) == 0){
            amount = BigDecimal.ONE;
        }

        EntrustOrder order = new EntrustOrder();
        switch (orderType){
            case 1:
                order.setUserType(2);
                order.setOrderNo("S" + DateFormatUtils.format(new Date(), DATETIME_FORMAT) + RandomStringUtils.randomNumeric(6));
                break;
            default:
                order.setUserType(1);
                order.setOrderNo("B" + DateFormatUtils.format(new Date(), DATETIME_FORMAT) + RandomStringUtils.randomNumeric(6));
        }
        order.setUserId(Long.valueOf(userId));
        order.setAmount(amount);
        order.setRemainAmount(amount);
        order.setPrice(price);

        return Result.status(entrustOrderService.createOrder(order));
    }

}
