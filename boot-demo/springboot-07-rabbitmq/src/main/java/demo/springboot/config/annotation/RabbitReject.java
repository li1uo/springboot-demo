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

}
