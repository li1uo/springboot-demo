package demo.rabbitmq.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LILUO
 * @date 2019/02/19
 */
@RestController
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    public static final String QUEUE = "default-queue";

    @Autowired
    private AmqpTemplate amqpTemplate;

    @PostMapping(value = "/send")
    public @ResponseBody String sendMsg(){
        amqpTemplate.convertAndSend("amq.direct", IndexController.QUEUE, "测试消息");
        return "发送成功";
    }

    /**
     * 发布订阅信息
     *
     * @return
     */
    @PostMapping(value = "/topic")
    public String sendTopicMsg(){
        amqpTemplate.convertAndSend("amq.fanout", "", "测试消息");
        return "订阅成功";
    }
}
