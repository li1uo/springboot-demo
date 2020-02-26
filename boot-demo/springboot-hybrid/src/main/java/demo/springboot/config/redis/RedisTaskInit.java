package demo.springboot.config.redis;

import demo.springboot.config.thread.RedisTask;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.time.Duration;
import java.util.concurrent.ScheduledFuture;

/**
 * @author LILUO
 * @date 2020/02/22
 */
@AllArgsConstructor
@Configuration
public class RedisTaskInit implements CommandLineRunner {

    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private RedisTemplate redisTemplate;

    @Override
    public void run(String... args) throws Exception {

        //ScheduledFuture scheduledFuture = threadPoolTaskScheduler.scheduleAtFixedRate(new TaskThread(), Duration.ofMillis(400));

        ScheduledFuture scheduledFuture = threadPoolTaskScheduler.scheduleAtFixedRate(new RedisTask(redisTemplate), Duration.ofMillis(1000));
    }
}
