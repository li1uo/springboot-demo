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
import java.util.concurrent.atomic.AtomicInteger;

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

    private static AtomicInteger num = new AtomicInteger(0);

    @Autowired
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
