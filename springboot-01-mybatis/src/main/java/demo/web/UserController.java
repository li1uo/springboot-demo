package demo.web;

import demo.domain.UserDO;
import demo.service.IUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author LILUO
 * @date 2018/5/9
 */
@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private IUserService userService;

    @GetMapping("/{userId}")
    public UserDO getUserById(@PathVariable Long userId){
        return userService.getUserById(userId);
    }

}
