package demo.springboot.config.annotation;

import java.lang.annotation.*;

/**
 * @author LILUO
 * @date 2018/11/14
 */
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TaskMonitor {

}
