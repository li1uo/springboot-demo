package demo.springboot.config.thread;

import demo.springboot.domain.MatchOrderData;
import demo.springboot.domain.MatchOrderMsg;
import demo.springboot.tool.util.JsonUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author LILUO
 * @date 2020/06/23
 */
@Slf4j
public class MatchOrderThread implements Runnable {

    /**锁(防止订单撮合拿到同一订单多次处理)**/
    public static Lock lock = new ReentrantLock();

    /**买单队列**/
    public static final String BUY_ORDER_QUEUE = "buy_order_queue";

    /**卖单队列**/
    public static final String SELL_ORDER_QUEUE = "sell_order_queue";

    public MatchOrderThread(RedisTemplate redisTemplate, RabbitTemplate rabbitTemplate){
        this.redisTemplate = redisTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }

    private RedisTemplate<String, Object> redisTemplate;

    private RabbitTemplate rabbitTemplate;

    @SneakyThrows
    @Override
    public void run() {

        while(true){
            long begin = System.currentTimeMillis();
            // 取买单队列中价格最高的
            List<Object> buyOrderList = new ArrayList<>(redisTemplate.opsForZSet().reverseRange(BUY_ORDER_QUEUE, 0, 0));

            // 取卖单队列中价格最低的
            List<Object> sellOrderList = new ArrayList<>(redisTemplate.opsForZSet().range(SELL_ORDER_QUEUE, 0, 0));

            if (CollectionUtils.isEmpty(buyOrderList) || CollectionUtils.isEmpty(sellOrderList)){
                // log.info("match order end, 订单处理完毕");
                Thread.sleep(1000);
                continue;
            }

            // MQ进行订单撮合
            MatchOrderMsg msg = new MatchOrderMsg();
            MatchOrderData buyOrder = (MatchOrderData)buyOrderList.get(0);
            MatchOrderData sellOrder = (MatchOrderData)sellOrderList.get(0);

            if (buyOrder.getUserId().equals(sellOrder.getUserId())){
                continue;
            }
            msg.setBuyOrder(buyOrder.getOrderNo());
            msg.setSellOrder(sellOrder.getOrderNo());
            rabbitTemplate.convertAndSend("amq.direct", "match_order", JsonUtil.toJson(msg));

            // 删除队列中该订单
            redisTemplate.opsForZSet().remove(BUY_ORDER_QUEUE, buyOrderList.get(0));
            redisTemplate.opsForZSet().remove(SELL_ORDER_QUEUE, sellOrderList.get(0));
            long end = System.currentTimeMillis();
            //log.info("match order consume: {} ms", end - begin);
        }
    }
}
