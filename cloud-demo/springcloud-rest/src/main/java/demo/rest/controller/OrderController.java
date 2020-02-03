package demo.rest.controller;

import demo.rest.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LILUO
 * @date 2019/02/13
 */
@RequestMapping(value = "/order")
@RestController
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping("/list")
    public List<String> getAllMember(){
        return orderService.getOrderList();
    }
}
