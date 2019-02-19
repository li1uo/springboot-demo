package demo.rabbitmq.core.config;

import demo.rabbitmq.core.controller.IndexController;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author LILUO
 * @date 2019/02/19
 */
@EnableScheduling
@Configuration
public class TaskConfig {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Scheduled(cron = "0/10 * * * * *")
    public void sendQueueMsg(){
        amqpTemplate.convertAndSend("amq.direct", IndexController.QUEUE, "测试信息");
    }
}
