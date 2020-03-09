package demo.springboot.config.ratelimit;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 限流属性
 *
 * @author LILUO
 * @date 2020/01/16
 */
@ConfigurationProperties(prefix = "rate-limiter")
@Data
public class RateLimiterProperties {

    /**
     * 是否开启
     */
    private boolean enable = false;

    /**
     * 限流类型
     */
    private RateLimiterType type = RateLimiterType.MEMORY;
}
