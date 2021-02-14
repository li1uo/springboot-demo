package demo.springboot.controller;

import demo.springboot.common.domain.Result;
import demo.springboot.domian.RocketConfig;
import demo.springboot.service.IRocketConfigService;
import lombok.AllArgsConstructor;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.web.bind.annotation.PostMapping;
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

    private IRocketConfigService rocketConfigService;

    @RequestMapping("/push")
    public Result push() {
        rocketMQTemplate.convertAndSend(ROCKET_DESTINATION, System.currentTimeMillis());
        return Result.success();
    }

    @PostMapping("/config/add")
    public Result add(RocketConfig rocketConfig) {
        return Result.status(rocketConfigService.addConfig(rocketConfig));
    }

    @PostMapping("/config/del")
    public Result delete(RocketConfig rocketConfig) {
        return Result.status(rocketConfigService.deleteConfig(rocketConfig.getConsumerGroup(), rocketConfig.getTopic(), rocketConfig.getTag()));
    }
}
