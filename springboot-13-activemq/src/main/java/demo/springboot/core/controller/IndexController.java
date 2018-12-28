package demo.springboot.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jms.Queue;

/**
 * @author LILUO
 * @date 2018/7/4
 */
@Controller
public class IndexController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue queue;

    @RequestMapping(value = "/index")
    public String index(){
        jmsTemplate.convertAndSend(queue,"1111");
        return "index";
    }
}
