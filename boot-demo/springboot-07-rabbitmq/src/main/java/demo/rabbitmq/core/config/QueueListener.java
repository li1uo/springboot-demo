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

import java.io.IOException;

/**
 * @author LILUO
 * @date 2019/02/19
 */
@Slf4j
@Configuration
public class QueueListener {

    /**
     * 1.rabbitmq 消费者接收数据精确到routing-key 比如日志系统queue同时绑定 log、debug、error三个routing-key到同一个redirect exchange上
     * 接收数据时可以使用三个listener 绑定不同的routing-key来接收不同级别的日志
     *
     * 2.redirect exchange支持多个queue使用同一个routing-key或一个queue使用多个routing-key
     *
     * bindings该写法会自动创建queue 和 exchange 并绑定queue到exchange上
     *
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("springboot_demo_default_queue"),
            exchange = @Exchange(value = "amq.direct", type = ExchangeTypes.DIRECT),
            key = "default-queue"
    ), ackMode = "MANUAL")
    public void defaultQueue(Message message, Channel channel) {
        //  如果手动ACK,消息会被监听消费,但是消息在队列中依旧存在,如果 未配置 acknowledge-mode 默认是会在消费完毕后自动ACK掉
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            log.debug("defaultQueue data: {}", message.toString());
            // 通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            try {
                // 处理失败,重新压入MQ
                channel.basicRecover();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("springboot_demo_default_queue"),
            exchange = @Exchange(value = "amq.direct", type = ExchangeTypes.DIRECT),
            key = "default-queue-1"
    ))
    public void receiveMsg(Message message) {
        log.debug("default-queue-1 data: {}", message.toString());
        throw new RuntimeException("11111");
    }
}
