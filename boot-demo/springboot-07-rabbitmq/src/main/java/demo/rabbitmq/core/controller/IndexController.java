package demo.rabbitmq.core.controller;

import demo.springboot.common.domain.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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

    /**
     * 向临时队列发送数据
     *
     * @return
     */
    @PostMapping("/temp")
    public Result tempMsg(){
        rabbitTemplate.convertAndSend("amq.fanout", "", "测试消息");
        return Result.success();
    }

    /**
     * 通过死信队列模拟延迟队列
     *
     * @return
     */
    @PostMapping("/ttl")
    public Result ttlMsg(){
        String currentTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        //rabbitTemplate.convertAndSend("delay.exchange", "delay.queue", "测试消息");
        // 设置消息过期时间
        rabbitTemplate.convertAndSend("delay.exchange", "delay.queue", currentTime, message -> {
            message.getMessageProperties().setExpiration("6000");
            return message;
        });
        return Result.success();
    }

    /**
     * 延迟队列
     *
     * @return
     */
    @PostMapping("/delay")
    public Result delayMsg(Integer ttl){
        String currentTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        // 设置消息过期时间
        rabbitTemplate.convertAndSend("customer_delay_exchange", "customer_delay_queue", currentTime, message -> {
            message.getMessageProperties().setDelay(ttl * 1000);
            return message;
        });
        return Result.success();
    }

}
