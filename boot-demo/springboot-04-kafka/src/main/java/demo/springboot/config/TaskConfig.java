package demo.springboot.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

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

    private static AtomicInteger num = new AtomicInteger(0);

    private KafkaTemplate kafkaTemplate;

    /**
     * 定时任务
     */
    @Scheduled(cron = "0/3 * * * * ?")
    public void sendMessageToKafka(){
        if (num.get() >= 3){
            System.exit(0);
        }
        String message = UUID.randomUUID().toString();
        ListenableFuture future = kafkaTemplate.send("test", message + "_1");
        /*future.addCallback(o ->
                logger.debug("send-消息发送成功：" + message), throwable -> logger.debug("消息发送失败：" + message)
        );*/
        num.addAndGet(1);
    }
}
