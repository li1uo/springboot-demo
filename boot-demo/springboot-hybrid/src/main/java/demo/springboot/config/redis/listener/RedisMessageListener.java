package demo.springboot.config.redis.listener;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * redis订阅监听器
 *
 * @author LILUO
 * @date 2020/02/26
 */
@Slf4j
@AllArgsConstructor
@Component
public class RedisMessageListener implements MessageListener {

    private RedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        Object messageStr = redisTemplate.getValueSerializer().deserialize(message.getBody());
        log.debug("redis topic: {} ...", messageStr);
    }
}
