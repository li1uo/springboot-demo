package demo.springboot.core.config.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author LILUO
 * @date 2018/11/14
 */
@Aspect
@Component
public class TaskMonitorAspect {

    private static final Logger logger = LoggerFactory.getLogger(TaskMonitorAspect.class);

    /**
     * TaskMonitor注解切点
     */
    @Pointcut("@annotation(demo.springboot.core.config.annotation.TaskMonitor)")
    public void taskAspect() {

    }

    /**
     * 方法执行的整个周期
     * @param point
     * @throws NoSuchMethodException
     */
    @Around("taskAspect()")
    public Object around(ProceedingJoinPoint point){
        // 获取执行方法(此方法为拿到实现类的方法)
        Signature sig = point.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        // 方法名
        String methodName = msig.getName();
        Object result = null;
        try{
            logger.debug("=====开始执行定时任务=====");
            // 执行目标方法
            result = point.proceed();
        } catch (Throwable e) {
            logger.error("=====定时任务异常=====");
        }finally {
            logger.debug("=====定时任务执行完毕=====");
        }

        return result;
    }
}
