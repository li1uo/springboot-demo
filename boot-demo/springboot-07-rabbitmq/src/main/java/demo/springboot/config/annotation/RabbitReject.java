package demo.springboot.config.annotation;

import java.lang.annotation.*;

/**
 * @author LILUO
 * @date 2020/06/14
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RabbitReject {

    /**
     * 是否重试 false 将会直接reject消息
     *
     * @return
     */
    boolean retry() default true;

    /**
     * 重试次数
     *
     * @return
     */
    int attempts() default 3;
}
