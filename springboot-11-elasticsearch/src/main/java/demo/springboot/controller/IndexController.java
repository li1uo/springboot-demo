package demo.springboot.controller;

import demo.springboot.common.domain.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LILUO
 * @date 2020/01/30
 */
@AllArgsConstructor
@Slf4j
@RestController
public class IndexController {

    @GetMapping("/index")
    public Result index(){
        return Result.success();
    }
}
