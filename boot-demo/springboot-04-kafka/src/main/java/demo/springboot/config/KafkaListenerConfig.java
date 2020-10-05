package demo.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * kafka监听器
 *
 * @author LILUO
 * @date 2020/10/04
 */
@Slf4j
@Configuration
public class KafkaListenerConfig {

    @KafkaListener(topics = {"test-topic"}, groupId = "test-topic")
    public void receive(ConsumerRecord consumerRecord){
        log.debug("消费者1: Receive: {}", consumerRecord.value());
    }

    /*@KafkaListener(topics = {"test-topic"}, groupId = "test-topic-2")
    public void receive2(String content){
        log.debug("消费者2: Receive: {}", content);
    }*/
}
