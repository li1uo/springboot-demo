package demo.springboot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * rabbitmq delay插件下 delay exchange配置
 *
 * @author LILUO
 * @date 2020/02/22
 */
@Configuration
public class DelayExchangeConfig {

    @Bean("customExchange")
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        // 自定义交换机
        return new CustomExchange("customer_delay_exchange", "x-delayed-message", true, false, args);
    }

    @Bean("customQueue")
    public Queue delayQueue() {
        return new Queue("customer_delay_queue", true, false, false);
    }

    @Bean
    public Binding bindingDelayExchangeAndQueue(@Qualifier("customExchange") CustomExchange customExchange,
                                                @Qualifier("customQueue") Queue queue) {
        return BindingBuilder.bind(queue).to(customExchange).with("customer_delay_queue").noargs();
    }
}
