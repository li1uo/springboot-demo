package demo.rabbitmq.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * @author LILUO
 * @date 2019/02/19
 */
@Configuration
public class RabbitmqListenerConfig {

    private Logger logger = LoggerFactory.getLogger(RabbitmqListenerConfig.class);

    /**
     * 接收rabbitmq的数据
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(value = @Queue("default-queue"), exchange = @Exchange("amq.direct"), key = {"default-queue"}))
    public void receiveMsg(Message message){
        logger.debug("接收到 {}", message.toString());
    }
}
