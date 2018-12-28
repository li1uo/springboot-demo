package demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * kafka监听任务
 * @author LILUO
 * @date 2018/8/13
 */
@Component
public class KafkaListenerConfig {

    private Logger logger = LoggerFactory.getLogger(KafkaListenerConfig.class);

    @KafkaListener(topics = {"test"}, groupId = "test-topic")
    public void receive(String content){
        logger.error("消费者1: Receive: {}", content);
    }

    @KafkaListener(topics = {"test"}, groupId = "test-topic-2")
    public void receive2(String content){
        logger.error("消费者2: Receive: {}", content);
    }

    @KafkaListener(topics = {"test"}, groupId = "test-topic-3")
    public void receive3(String content){
        logger.error("消费者3: Receive: {}", content);
    }
}
