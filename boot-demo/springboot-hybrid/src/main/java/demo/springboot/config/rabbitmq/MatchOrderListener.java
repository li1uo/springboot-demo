package demo.springboot.config.rabbitmq;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.rabbitmq.client.Channel;
import demo.springboot.domain.EntrustOrder;
import demo.springboot.domain.MatchOrderMsg;
import demo.springboot.service.IEntrustOrderService;
import demo.springboot.service.IMatchOrderService;
import demo.springboot.tool.util.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Objects;

/**
 * @author LILUO
 * @date 2019/06/23
 */
@AllArgsConstructor
@Slf4j
@Configuration
public class MatchOrderListener {

    private IMatchOrderService matchOrderService;

    private IEntrustOrderService entrustOrderService;

    /**
     * 撮合订单
     *
     * @param message
     * @param channel
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("match_order"),
            exchange = @Exchange(value = "amq.direct", type = ExchangeTypes.DIRECT),
            key = "match_order"
    ))
    public void matchOrder(Message message, Channel channel) {
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            MatchOrderMsg msg = JsonUtil.parse(message.getBody(), MatchOrderMsg.class);
            log.debug("matchOrder listener: {}", msg);
            EntrustOrder buyOrder = entrustOrderService.getOne(Wrappers.<EntrustOrder>lambdaQuery().eq(EntrustOrder::getOrderNo, msg.getBuyOrder()));
            EntrustOrder sellOrder = entrustOrderService.getOne(Wrappers.<EntrustOrder>lambdaQuery().eq(EntrustOrder::getOrderNo, msg.getSellOrder()));
            // 校验订单状态
            if (Objects.isNull(buyOrder) || Objects.isNull(sellOrder) || buyOrder.getOrderStatus() != 1 || sellOrder.getOrderStatus() != 1){
                channel.basicAck(deliveryTag, false);
                return;
            }
            // 订单撮合
            matchOrderService.matchOrder(buyOrder, sellOrder);

            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            try {
                // 处理失败,重新压入MQ
                channel.basicRecover();
            } catch (IOException e1) {
                log.error("matchOrder recover fail", e1);
            }
        }
    }
}
