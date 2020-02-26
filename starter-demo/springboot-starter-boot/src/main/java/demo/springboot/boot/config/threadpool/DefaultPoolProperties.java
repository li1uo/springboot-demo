package demo.springboot.boot.config.threadpool;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 线程池配置
 *
 * @author LILUO
 * @date 2019/12/27
 */
@Data
@ConfigurationProperties(prefix = "thread.pool")
public class DefaultPoolProperties {

    /** Set the ThreadPoolExecutor's core pool size. */
    private int corePoolSize = 10;

    /** Set the ThreadPoolExecutor's maximum pool size. */
    private int maxPoolSize = 200;

    /** Set the capacity for the ThreadPoolExecutor's BlockingQueue. */
    private int queueCapacity = 20;
}
