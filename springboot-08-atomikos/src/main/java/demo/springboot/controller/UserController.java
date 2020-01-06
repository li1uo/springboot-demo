package demo.springboot.controller;

import demo.springboot.domain.UserDO;
import demo.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

/**
 * @author LILUO
 * @date 2019/08/28
 */
@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/insert")
    public @ResponseBody String insert(){
        UserDO userDO = new UserDO();
        userDO.setUserName("1234");
        userDO.setUserPassword("2123123");
        userDO.setCreateBy(0L);
        userDO.setCreateTime(LocalDateTime.now());
        userService.insertUser(userDO);
        return "success";
    }
}
