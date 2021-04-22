package demo.springboot.config;

import demo.springboot.config.jwt.SecureUtil;
import demo.springboot.config.jwt.UserInfo;
import demo.springboot.domain.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author LILUO
 * @date 2021/04/20
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        // 获取令牌并根据令牌获取登录认证信息
        Authentication authentication = null;
        UserInfo userInfo = SecureUtil.getUser();
        if (Objects.nonNull(userInfo)) {
            authentication = new UsernamePasswordAuthenticationToken(userInfo.getUserName(), null, new ArrayList<>());
        }

        if (Objects.isNull(authentication)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        // 设置登录认证信息到上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
