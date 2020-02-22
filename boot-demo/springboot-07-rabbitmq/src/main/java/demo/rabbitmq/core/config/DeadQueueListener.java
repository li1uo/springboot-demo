package demo.rabbitmq.core.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * 死信队列消费者
 *
 * @author LILUO
 * @date 2020/02/22
 */
@Slf4j
@Configuration
public class DeadQueueListener {

    @RabbitListener(queues = "dlx.queue")
    public void deadLetterQueue(Message message) {
        log.debug("deadLetterQueue data: {}, currentTime: {}", new String(message.getBody()), DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
    }
}
