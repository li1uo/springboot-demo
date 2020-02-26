package demo.springboot.controller;

import demo.springboot.common.domain.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author LILUO
 * @date 2020/02/26
 */
@Slf4j
@AllArgsConstructor
@RestController
public class RedisController {

    private RedisTemplate redisTemplate;

    /**
     * redis队列提供者
     *
     * @return
     */
    @PostMapping("/redis/queue")
    public Result sendRedisQueue(){
        return Result.data(redisTemplate.opsForList().rightPush("redis-test-queue", UUID.randomUUID().toString()));
    }
}
