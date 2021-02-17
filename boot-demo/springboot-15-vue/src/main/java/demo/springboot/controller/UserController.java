package demo.springboot.controller;

import demo.springboot.common.domain.Result;
import demo.springboot.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LILUO
 * @date 2021/02/17
 */
@RequestMapping("/user")
@Controller
public class UserController {

    @GetMapping("/list")
    public @ResponseBody Result<List<User>> list() {
        List<User> list = new ArrayList<>();
        User user = new User();
        user.setUserName("li1uo");
        user.setMobile("13333333333");
        user.setPassword("123456");
        list.add(user);

        User user2 = new User();
        user2.setUserName("test");
        user2.setMobile("15555555555");
        user2.setPassword("233");
        list.add(user2);

        return Result.data(list);
    }
}
