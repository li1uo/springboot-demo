package demo.rabbitmq.core.controller;

import demo.springboot.common.domain.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author LILUO
 * @date 2019/02/19
 */
@Slf4j
@AllArgsConstructor
@RestController
public class IndexController {

    private RabbitTemplate rabbitTemplate;

    @PostMapping(value = "/send")
    public Result sendMsg(){
        rabbitTemplate.convertAndSend("amq.direct", "default-queue", "1-" + UUID.randomUUID().toString());

        rabbitTemplate.convertAndSend("amq.direct", "default-queue-1", "2-" + UUID.randomUUID().toString());
        return Result.success();
    }

    /**
     * 发布订阅信息
     *
     * @return
     */
    @PostMapping(value = "/topic")
    public Result sendTopicMsg(){
        rabbitTemplate.convertAndSend("demo_fanout_exchange", "", "测试消息");
        return Result.success();
    }
}
