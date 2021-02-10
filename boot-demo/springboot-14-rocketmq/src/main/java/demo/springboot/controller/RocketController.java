package demo.springboot.controller;

import demo.springboot.common.domain.Result;
import lombok.AllArgsConstructor;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LILUO
 * @date 2021/02/10
 */
@AllArgsConstructor
@RequestMapping("/rocket")
@RestController
public class RocketController {

    public static final String ROCKET_DESTINATION = "rocket-test";

    private RocketMQTemplate rocketMQTemplate;

    @RequestMapping("/push")
    public Result push() {
        rocketMQTemplate.convertAndSend(ROCKET_DESTINATION, System.currentTimeMillis());
        return Result.success();
    }
}
