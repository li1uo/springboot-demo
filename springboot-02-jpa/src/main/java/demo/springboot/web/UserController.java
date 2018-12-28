package demo.springboot.web;

import demo.springboot.domain.UserDO;
import demo.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LILUO
 * @date 2018/5/9
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "主页";
    }

    /**
     * 根据用户ID拿到用户信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "/{userId}",method = RequestMethod.GET)
    public  UserDO getUserInfo(@PathVariable Long userId){
        UserDO userDO = userService.getUserById(userId);
        System.out.println(userDO);
        return userDO;
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<UserDO> getUserList(){
       return userService.listUser();
    }
}
