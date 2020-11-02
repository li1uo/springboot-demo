package demo.springboot.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * @author LILUO
 * @date 2020/11/02
 */
@AllArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf
        http.csrf().disable()
            .authorizeRequests()
            // swagger
            .antMatchers("/swagger**/**").permitAll()
            .antMatchers("/webjars/**").permitAll()
            .antMatchers("/v2/**").permitAll()
            .antMatchers("/guest").permitAll()
             // 其他所有请求需要身份认证
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .defaultSuccessUrl("/index")
            .and()
            .logout().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 自定义用户校验规则, 获取数据库用户数据
        auth.userDetailsService(userDetailsService)
             // 密码使用不加密模式, 加密模式密码为 '{加密算法}加密密码'
            .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
