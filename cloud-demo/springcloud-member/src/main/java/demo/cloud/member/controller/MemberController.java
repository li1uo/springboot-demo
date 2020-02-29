package demo.cloud.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LILUO
 * @date 2019/02/13
 */
@RequestMapping("/member")
@RestController
public class MemberController {

    /**
     * 返回所有成员信息
     * @return
     */
    @GetMapping("/list")
    public @ResponseBody List<String> getMemberList(){
        List<String> list = new ArrayList<>();
        list.add("霞之丘诗羽");
        list.add("加藤惠");
        return list;
    }
}
