package demo.rabbitmq.core.config;

import demo.rabbitmq.core.controller.IndexController;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.UUID;

/**
 * @author LILUO
 * @date 2019/02/19
 */
@AllArgsConstructor
@EnableScheduling
@Configuration
public class TaskConfig {

    private RabbitTemplate rabbitTemplate;

    @Scheduled(cron = "0/5 * * * * *")
    public void sendQueueMsg(){
        rabbitTemplate.convertAndSend("amq.direct", IndexController.QUEUE, UUID.randomUUID().toString());
    }
}
