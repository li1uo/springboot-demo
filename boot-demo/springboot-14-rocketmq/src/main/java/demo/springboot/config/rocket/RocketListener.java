package demo.springboot.config.rocket;

import demo.springboot.controller.RocketController;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

/**
 * @author LILUO
 * @date 2021/02/10
 */
@Slf4j
@Configuration
public class RocketListener {

    @RocketMQMessageListener(consumerGroup = "consumer-group-1", topic = RocketController.ROCKET_DESTINATION, selectorExpression = "*", messageModel = MessageModel.CLUSTERING)
    @Service
    public class consumer implements RocketMQListener<String> {
        @Override
        public void onMessage(String s) {
            log.info("consumer rocket收到消息：{}", s);
        }
    }

    @RocketMQMessageListener(consumerGroup = "consumer-group-2", topic = RocketController.ROCKET_DESTINATION, selectorExpression = "*", messageModel = MessageModel.CLUSTERING)
    @Service
    public class consumer2 implements RocketMQListener<String> {
        @Override
        public void onMessage(String s) {
            log.info("consumer2 rocket收到消息：{}", s);
        }
    }
}
