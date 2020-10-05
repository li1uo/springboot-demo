package demo.springboot.controller;

import demo.springboot.common.domain.Result;
import demo.springboot.config.KafkaListenerConfig;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LILUO
 * @date 2020/10/05
 */
@AllArgsConstructor
@RestController
public class TestController {

    private KafkaTemplate kafkaTemplate;

    @PostMapping("/send/{msg}")
    public Result send(@PathVariable String msg){
        kafkaTemplate.send(KafkaListenerConfig.DEFAULT_TOPIC, msg);
        return Result.success();
    }
}
