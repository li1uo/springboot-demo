package demo.springboot.config;

import demo.springboot.config.aspect.AuthAspect;
import demo.springboot.config.handler.PermissionHandler;
import demo.springboot.config.handler.IPermissionHandler;
import demo.springboot.config.interceptor.TokenInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author LILUO
 * @date 2020/01/07
 */
@Configuration
public class SecureConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor())
                .excludePathPatterns("/token");
    }

    @Bean
    @ConditionalOnProperty(value = "auth.enable", havingValue = "true")
    public AuthAspect authAspect() {
        return new AuthAspect();
    }

    @Bean
    public IPermissionHandler permissionHandler() {
        return new PermissionHandler();
    }
}
