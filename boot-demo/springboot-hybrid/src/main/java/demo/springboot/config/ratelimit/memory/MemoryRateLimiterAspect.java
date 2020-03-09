package demo.springboot.config.ratelimit.memory;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import demo.springboot.common.exception.RateLimitException;
import demo.springboot.tool.util.StringPool;
import demo.springboot.tool.util.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * rateLimiter限流切面
 *
 * @author LILUO
 * @date 2020/01/16
 */
@Slf4j
@Aspect
public class MemoryRateLimiterAspect {

    private static final ThreadLocal<Method> methodThreadLocal = new ThreadLocal<>();

    private static final LoadingCache<String, com.google.common.util.concurrent.RateLimiter> loadingCache = CacheBuilder
            .newBuilder()
            .maximumSize(1000)
            .expireAfterAccess(1, TimeUnit.MINUTES)
            .build(new CacheLoader<String, com.google.common.util.concurrent.RateLimiter>() {
                @Override
                public com.google.common.util.concurrent.RateLimiter load(String key) {

                    Method method = methodThreadLocal.get();
                    RateLimiter anno = method.getAnnotation(RateLimiter.class);
                    if (anno != null) {
                        return com.google.common.util.concurrent.RateLimiter.create(anno.value());
                    }
                    return com.google.common.util.concurrent.RateLimiter.create(10);
                }
            });


    @Pointcut("@annotation(demo.springboot.config.ratelimit.memory.RateLimiter)")
    public void pointcut() {

    }

    @Around(value = "pointcut()")
    public Object auth(ProceedingJoinPoint pjp) throws Throwable {

        Object[] args = pjp.getArgs();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String ip = WebUtil.getIP(((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest());

        Method method = signature.getMethod();
        methodThreadLocal.set(method);

        StringBuilder builder = new StringBuilder();
        builder.append(ip);
        builder.append(StringPool.COLON);
        builder.append(method.getDeclaringClass().getName());
        builder.append(StringPool.COLON);
        builder.append(method.getName());

        com.google.common.util.concurrent.RateLimiter limiter = loadingCache.get(builder.toString());

        boolean flag = Objects.isNull(limiter) || limiter.tryAcquire();
        if (!flag){
            throw new RateLimitException();
        }

        return pjp.proceed(args);
    }
}
