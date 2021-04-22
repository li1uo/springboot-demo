package demo.springboot.controller;

import demo.springboot.common.domain.Result;
import demo.springboot.config.jwt.TokenUtil;
import demo.springboot.config.jwt.UserInfo;
import demo.springboot.domain.JwtUserDetail;
import demo.springboot.tool.util.WebUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LILUO
 * @date 2021/04/20
 */
@AllArgsConstructor
@RestController
public class LoginController {

    private AuthenticationManager authenticationManager;

    @RequestMapping("/login")
    public Result login(String userName, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName, password);
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(WebUtil.getRequest()));
        // 执行登录认证过程
        Authentication authentication = authenticationManager.authenticate(token);
        JwtUserDetail user = (JwtUserDetail)authentication.getPrincipal();

        // 认证成功存储认证信息到上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(user.getUsername());
        userInfo.setUserId(user.getUserId());
        userInfo.setAuthorities(user.getAuthorities());
        return Result.data(TokenUtil.createAuthInfo(userInfo));
    }
}
