package demo.rabbitmq.core.config;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

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

    //@Scheduled(cron = "0/5 * * * * *")
    public void sendQueueMsg(){
        rabbitTemplate.convertAndSend("amq.direct", "default-queue", UUID.randomUUID().toString());
    }
}
