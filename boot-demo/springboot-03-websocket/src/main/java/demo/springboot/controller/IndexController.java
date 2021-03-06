package demo.springboot.controller;

import demo.springboot.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
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
        String userName = RandomStringUtils.randomAlphabetic(6);
        String token = TokenUtil.generate(userName);
        request.setAttribute("token", token);
        request.getSession().setAttribute("userName", userName);
        return "index";
    }

    @GetMapping("/")
    public String defaultPage(){
        return "redirect:/index";
    }
}
