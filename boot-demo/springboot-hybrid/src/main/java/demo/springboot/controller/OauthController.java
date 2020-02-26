package demo.springboot.controller;

import demo.springboot.common.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LILUO
 * @date 2020/02/26
 */
@Slf4j
@RestController
public class OauthController {

    /**
     * 接收 oauth2 授权码模式code
     *
     * @param code
     * @return
     */
    @GetMapping("/oauth/code")
    public Result getCode(String code){
        log.info("oauth code: {}", code);
        return Result.success();
    }
}
