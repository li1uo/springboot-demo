package demo.springboot.controller;

import demo.springboot.common.domain.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * xss过滤
 *
 * @author LILUO
 * @date 2020/07/08
 */
@RequestMapping("/xss")
@RestController
public class XssController {

    @PostMapping("/submit")
    public Result<String> getResult(String str){
        return Result.data(str);
    }
}
