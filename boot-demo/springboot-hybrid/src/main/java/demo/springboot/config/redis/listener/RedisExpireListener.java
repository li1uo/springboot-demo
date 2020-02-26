package demo.springboot.config.redis.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * 监听redis key过期事件
 *
 * @author LILUO
 * @date 2020/02/26
 */
@Slf4j
@Component
public class RedisExpireListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String body = new String(message.getBody());
        String patterns = new String(pattern);
        log.debug("redis expire: 【patten】{},【message】{},【body】{}", patterns, message.toString(), body);
    }
}
