package demo.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

/**
 * @author LILUO
 * @date 2019/11/24
 */
@RestController
public class IndexController {

    @Autowired
    private Environment environment;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public String get() {
        return environment.getProperty("email.username");
    }
}
