package demo.springboot.controller;

import demo.springboot.common.domain.Result;
import demo.springboot.log.config.ApiLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LILUO
 * @date 2020/03/10
 */
@RestController
public class LogController {

    @ApiLog("测试接口")
    @GetMapping("/log")
    public Result log(){
        return Result.success();
    }
}
