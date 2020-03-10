package demo.springboot.log.config;

import demo.springboot.log.config.listener.ApiLogListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.servlet.DispatcherType;

/**
 * 日志设置
 *
 * @author LILUO
 * @date 2019/12/26
 */
@ConditionalOnWebApplication
@Configuration
public class LogConfig {

    @Bean
    public FilterRegistrationBean<LogTraceFilter> xssFilterRegistration() {
        FilterRegistrationBean<LogTraceFilter> registration = new FilterRegistrationBean<>();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new LogTraceFilter());
        registration.addUrlPatterns("/*");
        registration.setName("LogTraceFilter");
        registration.setOrder(Ordered.LOWEST_PRECEDENCE);
        return registration;
    }

    @Bean
    @ConditionalOnMissingBean(name = "apiLogListener")
    public ApiLogListener apiLogListener() {
        return new ApiLogListener();
    }
}
