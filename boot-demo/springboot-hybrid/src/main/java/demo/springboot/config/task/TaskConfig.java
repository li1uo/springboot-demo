package demo.springboot.config.task;

import demo.springboot.config.thread.MatchOrderThread;
import demo.springboot.service.IMatchOrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author LILUO
 * @date 2020/06/24
 */
@AllArgsConstructor
@Slf4j
@EnableAsync
@EnableScheduling
@Configuration
public class TaskConfig {

    private RedisTemplate<String, Object> redisTemplate;

    private IMatchOrderService matchOrderService;

    /**
     * 监控撮合队列
     */
    @Scheduled(cron = "0/1 * * * * ?")
    public void monitorMatchQueue(){
        long buyCount = redisTemplate.opsForZSet().zCard(MatchOrderThread.BUY_ORDER_QUEUE);
        long sellCount = redisTemplate.opsForZSet().zCard(MatchOrderThread.SELL_ORDER_QUEUE);
        int orderCount = matchOrderService.count();
        log.info("monitor match queue, buyQueue: {}, sellQueue: {}, orderCount: {}", buyCount, sellCount, orderCount);
    }
}
