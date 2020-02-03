package demo.feign.core.controller;

import demo.feign.core.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LILUO
 * @date 2019/02/13
 */
@RequestMapping(value = "/order")
@RestController
public class OrderController {

    @Autowired
    private IMemberService memberService;

    @GetMapping(value = "/member/list")
    public @ResponseBody List getMemberList(){
        return memberService.getMemberList();
    }
}
