package demo.springboot.config.aspect;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @author LILUO
 * @date 2020/06/14
 */
@Slf4j
@Aspect
@Component
public class RabbitExtendAspect {

    @Pointcut("@annotation(demo.springboot.config.annotation.RabbitReject)")
    public void pointcut() {

    }

    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint pjp) {
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
                // 拒绝消息
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        return result;
    }
}
