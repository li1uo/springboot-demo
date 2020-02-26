package demo.springboot.config.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;

/**
 * redis队列消费者
 *
 * @author LILUO
 * @date 2020/02/22
 */
@Slf4j
public class RedisTask implements Runnable {

    public RedisTask(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    private RedisTemplate redisTemplate;

    @Override
    public void run() {
        /*log.info("redis queue begin: {}", System.currentTimeMillis());
        try{
            Thread.sleep(1000);
        }catch (Exception e){

        }*/
        Object value = redisTemplate.opsForList().leftPop("redis-test-queue");
        if (Objects.nonNull(value)){
            log.info("redis queue data: {}", value);
        }
    }
}
