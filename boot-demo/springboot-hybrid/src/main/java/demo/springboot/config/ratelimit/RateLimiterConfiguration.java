package demo.springboot.config.ratelimit;

import demo.springboot.config.ratelimit.memory.MemoryRateLimiterAspect;
import demo.springboot.config.ratelimit.redis.RedisRateLimiterAspect;
import demo.springboot.config.ratelimit.redis.RedisRateLimiterClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.util.List;

/**
 * 限流配置
 *
 * @author LILUO
 * @date 2020/01/16
 */
@ConditionalOnProperty(value = "rate-limiter.enable", havingValue = "true")
@EnableConfigurationProperties(RateLimiterProperties.class)
@Configuration
public class RateLimiterConfiguration {

    /**
     * 内存限流设置
     */
    @ConditionalOnProperty(value = "rate-limiter.type", havingValue = "memory", matchIfMissing = true)
    class MemoryRateLimiterConfiguration{

        @Bean
        public MemoryRateLimiterAspect memoryRateLimitAspect(){
            return new MemoryRateLimiterAspect();
        }
    }


    /**
     * redis限流设置
     */
    @ConditionalOnProperty(value = "rate-limiter.type", havingValue = "redis")
    class RedisRateLimiterConfiguration{

        @SuppressWarnings("unchecked")
        private RedisScript<List<Long>> redisRateLimiterScript() {
            DefaultRedisScript redisScript = new DefaultRedisScript<>();
            redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("META-INF/scripts/system_rate_limiter.lua")));
            redisScript.setResultType(List.class);
            return redisScript;
        }

        @Bean
        @ConditionalOnMissingBean
        public RedisRateLimiterClient redisRateLimiter(StringRedisTemplate redisTemplate, Environment environment) {
            RedisScript<List<Long>> redisRateLimiterScript = redisRateLimiterScript();
            return new RedisRateLimiterClient(redisTemplate, redisRateLimiterScript, environment);
        }

        @Bean
        @ConditionalOnMissingBean
        public RedisRateLimiterAspect redisRateLimiterAspect(RedisRateLimiterClient rateLimiterClient) {
            return new RedisRateLimiterAspect(rateLimiterClient);
        }
    }
}
