package demo.springboot.controller;

import demo.springboot.common.domain.Result;
import demo.springboot.config.jwt.SecureUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LILUO
 * @date 2020/11/02
 */
@RestController
public class IndexController {

    @RequestMapping("/index")
    public Result<String> index(){
        SecureUtil.getUser().getUserId();
        return Result.data("this is index page");
    }

    @RequestMapping("/guest")
    public Result<String> guest(){
        return Result.data("this guest interface");
    }
}
