package demo.springboot.controller;

import demo.springboot.model.R;
import demo.springboot.model.UserInfo;
import demo.springboot.utils.SecureUtil;
import demo.springboot.utils.TokenUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LILUO
 * @date 2020/01/07
 */
@RestController
public class UserController {

    public static final String USER_TARGET = "admin";
    public final static String USER_NOT_FOUND = "用户名或密码错误";

    @PostMapping("/token")
    public R token(String userName, String password){
        // 校验账号
        if (userName.equals(USER_TARGET) && password.equals(USER_TARGET)){
            UserInfo userInfo = new UserInfo();
            userInfo.setId(1l);
            userInfo.setUserName(userName);
            return R.data(TokenUtil.createAuthInfo(userInfo));
        }

        return R.fail(USER_NOT_FOUND);
    }


    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/user/info")
    public UserInfo info(){
        return SecureUtil.getUser();
    }
}
