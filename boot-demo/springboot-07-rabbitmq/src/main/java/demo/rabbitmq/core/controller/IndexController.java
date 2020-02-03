package demo.rabbitmq.core.controller;

import demo.springboot.common.domain.Result;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author LILUO
 * @date 2019/02/19
 */
@AllArgsConstructor
@RestController
public class IndexController {

    public static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    public static final String QUEUE = "default-queue";

    private RabbitTemplate rabbitTemplate;

    @PostMapping(value = "/send")
    public Result sendMsg(){
        rabbitTemplate.convertAndSend("amq.direct", IndexController.QUEUE, UUID.randomUUID().toString());
        return Result.success();
    }

    /**
     * 发布订阅信息
     *
     * @return
     */
    @PostMapping(value = "/topic")
    public Result sendTopicMsg(){
        rabbitTemplate.convertAndSend("amq.fanout", "", "测试消息");
        return Result.success();
    }
}
