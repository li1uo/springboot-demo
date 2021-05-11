package demo.springboot.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

/**
 * @author LILUO
 * @date 2019/11/24
 */
@AllArgsConstructor
@RestController
public class IndexController {

    private Environment environment;

    @GetMapping("/get")
    public @ResponseBody String get() {
        return environment.getProperty("email.username");
    }
}
