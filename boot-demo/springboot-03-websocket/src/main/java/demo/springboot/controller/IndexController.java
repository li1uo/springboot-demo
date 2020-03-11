package demo.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LILUO
 * @date 2018/7/2
 */
@Slf4j
@Controller
public class IndexController {

    @GetMapping("/index")
    public String index(HttpServletRequest request){
        request.setAttribute("userName","liluo");
        return "index";
    }

    @GetMapping("/")
    public String defaultPage(){
        return "redirect:/index";
    }
}
