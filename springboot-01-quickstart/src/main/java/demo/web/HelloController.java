package demo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LILUO
 * @date 2018/5/8
 */
@RestController
public class HelloController {

    @GetMapping(value = "/hello")
    public String index(){
        return "springboot  启动！";
    }
}
