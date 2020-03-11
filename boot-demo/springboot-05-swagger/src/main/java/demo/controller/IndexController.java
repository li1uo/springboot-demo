package demo.controller;

import demo.springboot.common.domain.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LILUO
 * @date 2018/9/15
 */
@Api(value = "主页")
@RequestMapping(value = "/index")
@RestController
public class IndexController {

    @ApiOperation(value = "swagger测试")
    @GetMapping("/test")
    public Result test(){
        return Result.data("swagger测试");
    }
}
