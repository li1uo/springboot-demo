package demo.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LILUO
 * @date 2018/5/13
 */
@RestController
public class IndexController {

    @RequestMapping(value = "/index")
    public String index(){
        return "主页";
    }
}
