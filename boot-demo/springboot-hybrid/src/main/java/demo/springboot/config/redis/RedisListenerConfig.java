package demo.springboot.config.redis;

import demo.springboot.config.redis.listener.RedisExpireListener;
import demo.springboot.config.redis.listener.RedisMessageListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * @author LILUO
 * @date 2020/02/26
 */
@Configuration
public class RedisListenerConfig {

    /**
     * redis监听容器
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory,
                                                                       RedisMessageListener redisMessageListener,
                                                                       RedisExpireListener redisExpireListener,
                                                                       Executor listenerExecutor){
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        // 使用线程池管理
        redisMessageListenerContainer.setTaskExecutor(listenerExecutor);
        // 监听器集合
        Map listenerMap = new HashMap<>(16);

        // 监听器1的订阅操作
        List<Topic> redisMessageList = new ArrayList<>();
        // 监听器1使用普通订阅，订阅具体的频道
        ChannelTopic redisMessageTopic = new ChannelTopic("redis-email");
        // 匹配订阅
        //PatternTopic patternTopic = new PatternTopic("redis-test*");
        redisMessageList.add(redisMessageTopic);

        // 监听器2
        List<Topic> redisExpireList = new ArrayList<>();
        PatternTopic redisExpirePatternTopic = new PatternTopic("__key*__:expired");
        redisExpireList.add(redisExpirePatternTopic);

        // 加入监听器
        listenerMap.put(redisMessageListener,redisMessageList);
        listenerMap.put(redisExpireListener,redisExpireList);

        redisMessageListenerContainer.setMessageListeners(listenerMap);
        return redisMessageListenerContainer;
    }
}
