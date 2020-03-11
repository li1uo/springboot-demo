package demo.springboot.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import demo.springboot.common.domain.Result;
import demo.springboot.config.ratelimit.memory.RateLimiter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author LILUO
 * @date 2020/02/26
 */
@Api(value = "redis", tags = "redis接口")
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
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "redis队列插入接口")
    @PostMapping("/redis/queue")
    public Result sendRedisQueue(){
        return Result.data(redisTemplate.opsForList().rightPush("redis-test-queue", UUID.randomUUID().toString()));
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    @demo.springboot.config.ratelimit.redis.RateLimiter(value = "test-currentTime", max = 3, timeUnit = TimeUnit.SECONDS)
    @RateLimiter(3)
    @GetMapping("/currentTime")
    public Result currentTime(){
        return Result.success();
    }
}
