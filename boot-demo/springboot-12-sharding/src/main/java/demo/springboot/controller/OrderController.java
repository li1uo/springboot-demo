package demo.springboot.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import demo.springboot.common.domain.Result;
import demo.springboot.domain.Order;
import demo.springboot.service.IOrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LILUO
 * @date 2020/09/04
 */
@AllArgsConstructor
@RequestMapping("/order")
@RestController
public class OrderController {

    private IOrderService orderService;

    @PostMapping("/create")
    public Result createOrder(String base, String quote){
        Order order = new Order();
        order.setBaseSymbol(base);
        order.setQuoteSymbol(quote);
        order.setSymbol(base + quote);

        return Result.status(orderService.save(order));
    }

    @GetMapping("/query")
    public Result<Order> query(String base, String quote, Long id){
        return Result.data(orderService.getOne(Wrappers.<Order>lambdaQuery().eq(Order::getBaseSymbol, base).eq(Order::getQuoteSymbol, quote).eq(Order::getId, id)));
    }
}
