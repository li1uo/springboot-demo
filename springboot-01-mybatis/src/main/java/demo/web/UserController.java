package demo.web;

import demo.domain.UserDO;
import demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author LILUO
 * @date 2018/5/9
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/index")
    public String index(){
        return "主页";
    }

    @RequestMapping(value = "/{userId}")
    public UserDO getUserById(@PathVariable Long userId){
        return userService.getUserById(userId);
    }

}
