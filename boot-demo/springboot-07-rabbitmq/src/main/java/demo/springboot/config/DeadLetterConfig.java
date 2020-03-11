package demo.springboot.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 死信队列设置
 *
 * @author LILUO
 * @date 2020/02/22
 */
@Configuration
public class DeadLetterConfig {

    public static final String DELAY_EXCHANGE_NAME = "delay.exchange";
    public static final String DELAY_QUEUE_NAME = "delay.queue";
    public static final String DELAY_QUEUE_ROUTING_KEY = "delay.queue";
    public static final String DEAD_LETTER_EXCHANGE = "dlx.exchange";
    public static final String DEAD_LETTER_QUEUE_ROUTING_KEY = "dlx.queue";
    public static final String DEAD_LETTER_QUEUE_NAME = "dlx.queue";

    /**
     * 声明延时Exchange
     *
     * @return
     */
    @Bean("delayExchange")
    public DirectExchange delayExchange(){
        return new DirectExchange(DELAY_EXCHANGE_NAME);
    }

    /**
     * 声明死信Exchange
     *
     * @return
     */
    @Bean("deadLetterExchange")
    public DirectExchange deadLetterExchange(){
        return new DirectExchange(DEAD_LETTER_EXCHANGE);
    }

    /**
     * 声明延时队列A 延时10s
     * 并绑定到对应的死信交换机
     *
     * @return
     */
    @Bean("delayQueue")
    public Queue delayQueueA(){
        Map<String, Object> args = new HashMap<>(16);
        // x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        // x-dead-letter-routing-key  这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUE_ROUTING_KEY);
        // x-message-ttl  声明队列的TTL(队列级别)
        //args.put("x-message-ttl", 6000);
        return QueueBuilder.durable(DELAY_QUEUE_NAME).withArguments(args).build();
    }

    /**
     * 声明死信队列 用于接收延时10s处理的消息
     *
     * @return
     */
    @Bean("deadLetterQueue")
    public Queue deadLetterQueueA(){
        return new Queue(DEAD_LETTER_QUEUE_NAME);
    }

    /**
     * 声明延时队列绑定关系
     *
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding delayBindingA(@Qualifier("delayQueue") Queue queue,
                                 @Qualifier("delayExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(DELAY_QUEUE_ROUTING_KEY);
    }

    /**
     * 声明死信队列绑定关系
     *
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding deadLetterBindingA(@Qualifier("deadLetterQueue") Queue queue,
                                      @Qualifier("deadLetterExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(DEAD_LETTER_QUEUE_ROUTING_KEY);
    }
}
