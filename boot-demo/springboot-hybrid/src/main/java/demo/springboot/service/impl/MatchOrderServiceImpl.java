package demo.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import demo.springboot.config.thread.MatchOrderThread;
import demo.springboot.domain.EntrustOrder;
import demo.springboot.domain.EntrustOrderLog;
import demo.springboot.domain.MatchOrder;
import demo.springboot.domain.MatchOrderData;
import demo.springboot.mapper.EntrustOrderLogMapper;
import demo.springboot.mapper.MatchOrderMapper;
import demo.springboot.service.IEntrustOrderService;
import demo.springboot.service.IMatchOrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author LILUO
 * @date 2020/06/23
 */
@AllArgsConstructor
@Service
public class MatchOrderServiceImpl extends ServiceImpl<MatchOrderMapper, MatchOrder> implements IMatchOrderService {

    private IEntrustOrderService entrustOrderService;

    private EntrustOrderLogMapper logMapper;

    private RedisTemplate<String, Object> redisTemplate;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean matchOrder(EntrustOrder buyOrder, EntrustOrder sellOrder) {
        if (Objects.isNull(buyOrder) || Objects.isNull(sellOrder) || buyOrder.getOrderStatus() == 0 || sellOrder.getOrderStatus() == 0 ||
                buyOrder.getOrderStatus() == 3 || sellOrder.getOrderStatus() == 3){
            return true;
        }

        // 部分交易委托订单放回redis继续挂出
        boolean buyOrderFlag = true;
        boolean sellOrderFlag = true;

        BigDecimal amount;
        int result = buyOrder.getRemainAmount().compareTo(sellOrder.getRemainAmount());
        switch (result){
            case 1:
                amount = sellOrder.getRemainAmount();
                break;
            default:
                amount = buyOrder.getRemainAmount();
        }

        // 修改原委托单
        // 修改买单
        buyOrder.setOrderStatus(2);
        if (buyOrder.getRemainAmount().compareTo(amount) <= 0){
            buyOrder.setOrderStatus(3);
            buyOrderFlag = false;
        }
        buyOrder.setRemainAmount(buyOrder.getRemainAmount().subtract(amount));
        buyOrder.setModifyTime(LocalDateTime.now());
        if (!entrustOrderService.updateById(buyOrder)){
            throw new RuntimeException("修改买单失败");
        }

        // 修改卖单
        sellOrder.setOrderStatus(2);
        if (sellOrder.getRemainAmount().compareTo(amount) <= 0){
            sellOrder.setOrderStatus(3);
            sellOrderFlag = false;
        }
        sellOrder.setRemainAmount(sellOrder.getRemainAmount().subtract(amount));
        sellOrder.setModifyTime(LocalDateTime.now());
        if (!entrustOrderService.updateById(sellOrder)){
            throw new RuntimeException("修改卖单失败");
        }

        EntrustOrderLog buyLog = new EntrustOrderLog();
        buyLog.setOrderId(buyOrder.getId());
        buyLog.setOrderNo(buyOrder.getOrderNo());
        buyLog.setType(2);
        logMapper.insert(buyLog);

        EntrustOrderLog sellLog = new EntrustOrderLog();
        sellLog.setOrderId(sellOrder.getId());
        sellLog.setOrderNo(sellOrder.getOrderNo());
        sellLog.setType(2);
        logMapper.insert(sellLog);

        MatchOrder matchOrder = new MatchOrder();
        matchOrder.setBuyOrderNo(buyOrder.getOrderNo());
        matchOrder.setBuyUserId(buyOrder.getUserId());
        matchOrder.setSellOrderNo(sellOrder.getOrderNo());
        matchOrder.setSellUserId(sellOrder.getUserId());
        matchOrder.setAmount(amount);
        // 撮合价格以卖家出售价格为准
        matchOrder.setPrice(sellOrder.getPrice());
        baseMapper.insert(matchOrder);

        // 部分成交订单放回redis
        if (buyOrderFlag){
            MatchOrderData data = new MatchOrderData();
            data.setOrderNo(buyOrder.getOrderNo());
            data.setUserId(buyOrder.getUserId());
            redisTemplate.opsForZSet().add(MatchOrderThread.BUY_ORDER_QUEUE, data, buyOrder.getPrice().doubleValue());
        }

        if (sellOrderFlag){
            MatchOrderData data = new MatchOrderData();
            data.setOrderNo(sellOrder.getOrderNo());
            data.setUserId(sellOrder.getUserId());
            redisTemplate.opsForZSet().add(MatchOrderThread.SELL_ORDER_QUEUE, data, sellOrder.getPrice().doubleValue());
        }

        return true;
    }
}
