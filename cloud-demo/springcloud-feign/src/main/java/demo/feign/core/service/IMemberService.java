package demo.feign.core.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * member引用
 * @author LILUO
 * @date 2019/02/13
 */
@FeignClient(name = "service-member", path = "/member")
public interface IMemberService {

    /**
     * 获取所有成员信息
     * @return
     */
    @GetMapping(value = "/list")
    List<String> getMemberList();
}
