package demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.UUID;

/**
 * 定时任务向kafka中传送数据
 * @author LILUO
 * @date 2018/8/13
 */
@EnableKafka
@EnableScheduling
@Component
public class TaskConfig {

    private Logger logger = LoggerFactory.getLogger(TaskConfig.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 定时任务
     */
    @Scheduled(cron = "0/1 * * * * ?")
    public void sendMessageToKafka(){
        String message = UUID.randomUUID().toString();
        ListenableFuture future = kafkaTemplate.send("test", message + "_1");
        /*future.addCallback(o ->
                logger.debug("send-消息发送成功：" + message), throwable -> logger.debug("消息发送失败：" + message)
        );*/
        ListenableFuture future2 = kafkaTemplate.send("test", message + "_2");
        /*future2.addCallback(o ->
                logger.debug("send-消息发送成功：" + message), throwable -> logger.debug("消息发送失败：" + message)
        );*/
        ListenableFuture future3 = kafkaTemplate.send("test", message + "_3");
        /*future3.addCallback(o ->
                logger.debug("send-消息发送成功：" + message), throwable -> logger.debug("消息发送失败：" + message)
        );*/
        ListenableFuture future4 = kafkaTemplate.send("test", message + "_4");
        /*future4.addCallback(o ->
                logger.debug("send-消息发送成功：" + message), throwable -> logger.debug("消息发送失败：" + message)
        );*/
    }
}
