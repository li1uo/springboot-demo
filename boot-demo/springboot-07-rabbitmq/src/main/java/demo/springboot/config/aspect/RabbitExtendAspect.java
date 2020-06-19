package demo.springboot.config.aspect;

import com.rabbitmq.client.Channel;
import demo.springboot.config.annotation.RabbitReject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.amqp.core.Message;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @author LILUO
 * @date 2020/06/14
 */
@AllArgsConstructor
@Slf4j
@Aspect
@Component
public class RabbitExtendAspect {

    private StringRedisTemplate redisTemplate;

    @Around(value = "@annotation(rabbitReject)")
    public Object around(ProceedingJoinPoint pjp, RabbitReject rabbitReject) {
        String className = pjp.getSignature().getDeclaringTypeName();
        Object[] args = pjp.getArgs();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        // 类名 + 方法名
        String point = className + "." + method.getName();

        // 获取方法参数
        List<Object> list = Arrays.asList(args);
        Message message = null;
        Channel channel = null;
        for (Object parameter :list) {
            if (parameter instanceof Message){
                message = (Message)parameter;
            }

            if (parameter instanceof Channel){
                channel = (Channel)parameter;
            }
        }

        Object result = null;
        try{
            result = pjp.proceed(args);
        } catch (Throwable e){
            log.error(point + " error", e);
            try {
                // 如果不重试, 直接抛弃消息
                if (!rabbitReject.retry()){
                    channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
                    return result;
                }

                String target = message.getMessageProperties().getMessageId();

                String attemptCount = redisTemplate.opsForValue().get(target);
                if (StringUtils.isNotBlank(attemptCount) && Integer.valueOf(attemptCount) >= rabbitReject.attempts()){
                    channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
                    return result;
                }

                channel.basicRecover();
                redisTemplate.opsForValue().increment(target);

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        return result;
    }
}
