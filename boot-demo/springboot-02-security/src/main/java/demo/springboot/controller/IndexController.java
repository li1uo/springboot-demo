package demo.springboot.controller;

import demo.springboot.common.domain.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LILUO
 * @date 2020/11/02
 */
@RestController
public class IndexController {

    @GetMapping("/index")
    public Result<String> index(){
        return Result.data("this is index page");
    }
}
