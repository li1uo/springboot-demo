package demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LILUO
 * @date 2018/9/15
 */
@Api(value = "主页")
@RequestMapping(value = "/index")
@RestController
public class IndexController {

    @ApiOperation(value = "获取测试数据")
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        return "测试swagger2333";
    }
}
