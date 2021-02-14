package demo.springboot.config;

import demo.springboot.domian.RocketConfig;
import demo.springboot.tool.util.SpringUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.autoconfigure.RocketMQProperties;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author LILUO
 * @date 2021/02/14
 */
@Slf4j
public class RocketUtil {

    private static Map<String, DefaultMQPushConsumer> consumerMap = new ConcurrentHashMap<>(16);

    public static synchronized void createConsumer(RocketConfig config) {
        String consumerGroup = config.getConsumerGroup();
        String topic = config.getTopic();
        String name = String.format("%s_%s", consumerGroup, topic);
        consumerMap.put(name, initConsumer(config));
    }

    public static synchronized void close(String name) {
        consumerMap.get(name).shutdown();
        consumerMap.remove(name);
    }


    /**
     * 根据rocket配置初始化消费者
     *
     * @param rocketConfig
     */
    @SneakyThrows
    public static DefaultMQPushConsumer initConsumer(RocketConfig rocketConfig) {
        RocketMQProperties rocketMQProperties = SpringUtil.getBean(RocketMQProperties.class);
        // 声明并初始化一个consumer
        // 需要一个consumer group名字作为构造方法的参数，这里为consumer1
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(rocketConfig.getConsumerGroup());

        // 同样也要设置NameServer地址
        consumer.setNamesrvAddr(rocketMQProperties.getNameServer());

        // 这里设置的是一个consumer的消费策略
        // CONSUME_FROM_LAST_OFFSET 默认策略，从该队列最尾开始消费，即跳过历史消息
        // CONSUME_FROM_FIRST_OFFSET 从队列最开始开始消费，即历史消息（还储存在broker的）全部消费一遍
        // CONSUME_FROM_TIMESTAMP 从某个时间点开始消费，和setConsumeTimestamp()配合使用，默认是半个小时以前
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        // 设置consumer所订阅的Topic和Tag，*代表全部的Tag
        consumer.subscribe(rocketConfig.getTopic(), rocketConfig.getTag());

        //设置一个Listener，主要进行消息的逻辑处理
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {

                //log.info("{}  Receive New Messages: {}", Thread.currentThread().getName(), JSON.toJSONString(msgs));
                log.info("{} receive message: {}", consumer.getConsumerGroup(), new String(msgs.get(0).getBody()));

                // 返回消费状态
                // CONSUME_SUCCESS 消费成功
                // RECONSUME_LATER 消费失败，需要稍后重新消费
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        // 调用start()方法启动consumer
        consumer.start();
        return consumer;
    }
}
