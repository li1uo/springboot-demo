package demo.springboot.controller;

import demo.springboot.common.domain.Result;
import demo.springboot.domain.User;
import demo.springboot.service.IUserService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @author LILUO
 * @date 2020/09/01
 */
@AllArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private IUserService userService;

    @PostMapping("/create")
    public Result create(){
        User user = new User();
        user.setUserName(StringUtils.replace(UUID.randomUUID().toString(), "-", ""));
        return Result.status(userService.save(user));
    }

    @GetMapping("/list")
    public Result<List<User>> list(){
        return Result.data(userService.list());
    }
}
