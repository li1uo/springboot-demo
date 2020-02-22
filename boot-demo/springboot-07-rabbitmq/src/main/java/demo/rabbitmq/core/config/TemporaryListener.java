package demo.rabbitmq.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * @author LILUO
 * @date 2020/02/22
 */
@Slf4j
@Configuration
public class TemporaryListener {

    /**
     * 非持久化临时队列
     *
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(durable = "false", exclusive = "false", autoDelete = "true"),
            exchange = @Exchange(value = "amq.fanout", type = ExchangeTypes.FANOUT)
    ))
    public void tempQueue(Message message) {
        log.debug("temp queue data: {}", message.toString());
    }
}
