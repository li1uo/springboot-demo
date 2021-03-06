package demo.springboot.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.UUID;

/**
 * 定时任务
 *
 * @author LILUO
 * @date 2020/10/04
 */
@Slf4j
@AllArgsConstructor
@EnableScheduling
@Configuration
public class TaskConfig {

    private KafkaTemplate kafkaTemplate;

    //@Scheduled(cron = "0/3 * * * * ?")
    public void sendMessageToKafka(){
        String message = UUID.randomUUID().toString();
        ListenableFuture future = kafkaTemplate.send("test-topic", message);
        /*future.addCallback(o ->
                logger.debug("send-消息发送成功：" + message), throwable -> logger.debug("消息发送失败：" + message)
        );*/
    }
}
