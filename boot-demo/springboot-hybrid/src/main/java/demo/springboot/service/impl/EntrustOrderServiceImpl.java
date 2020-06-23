package demo.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import demo.springboot.config.thread.MatchOrderThread;
import demo.springboot.domain.EntrustOrder;
import demo.springboot.domain.EntrustOrderLog;
import demo.springboot.domain.MatchOrder;
import demo.springboot.domain.MatchOrderData;
import demo.springboot.mapper.EntrustOrderLogMapper;
import demo.springboot.mapper.EntrustOrderMapper;
import demo.springboot.service.IEntrustOrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author LILUO
 * @date 2020/06/23
 */
@AllArgsConstructor
@Service
public class EntrustOrderServiceImpl extends ServiceImpl<EntrustOrderMapper, EntrustOrder> implements IEntrustOrderService {

    private RedisTemplate<String, Object> redisTemplate;

    private EntrustOrderLogMapper logMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean createOrder(EntrustOrder order) {
        baseMapper.insert(order);

        // 插入记录
        EntrustOrderLog log = new EntrustOrderLog();
        log.setOrderId(order.getId());
        log.setOrderNo(order.getOrderNo());
        log.setType(1);
        logMapper.insert(log);

        // 插入redis买卖单队列
        MatchOrderData data = new MatchOrderData();
        data.setOrderNo(order.getOrderNo());
        data.setUserId(order.getUserId());
        if (order.getUserType() == 1){
            redisTemplate.opsForZSet().add(MatchOrderThread.BUY_ORDER_QUEUE, data, order.getPrice().doubleValue());
        }else{
            redisTemplate.opsForZSet().add(MatchOrderThread.SELL_ORDER_QUEUE, data, order.getPrice().doubleValue());
        }
        return true;
    }
}
