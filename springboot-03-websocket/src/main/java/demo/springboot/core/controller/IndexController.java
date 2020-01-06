package demo.springboot.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LILUO
 * @date 2018/7/2
 */
@Controller
public class IndexController {

    private static final Logger log = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private SimpUserRegistry simpUserRegistry;

    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request){
        System.out.println("进入index  当前用户人数: " + simpUserRegistry.getUserCount());
        request.setAttribute("userName","liluo");
        return "index";
    }

    @RequestMapping(value = "/")
    public String defaultPage(){
        return "redirect:/index";
    }

    @RequestMapping(value = "/test")
    public String test(){
        System.out.println("进入test 当前用户人数: " + simpUserRegistry.getUserCount());
        return "test";
    }
}
