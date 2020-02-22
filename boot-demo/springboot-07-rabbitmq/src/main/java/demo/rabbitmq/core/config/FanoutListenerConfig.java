package demo.rabbitmq.core.config;

import com.rabbitmq.client.Channel;
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
public class FanoutListenerConfig {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("demo_fanout_queue_1"),
            exchange = @Exchange(value = "demo_fanout_exchange", type = ExchangeTypes.FANOUT),
            key = "default-queue"
    ), ackMode = "MANUAL")
    public void fanoutQueue_1(Message message, Channel channel) {
        log.debug("fanoutQueue-1 data: {}", message.toString());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("demo_fanout_queue_2"),
            exchange = @Exchange(value = "demo_fanout_exchange", type = ExchangeTypes.FANOUT),
            key = "default-queue"
    ), ackMode = "MANUAL")
    public void fanoutQueue_2(Message message, Channel channel) {
        log.debug("fanoutQueue-2 data: {}", message.toString());
    }
}
