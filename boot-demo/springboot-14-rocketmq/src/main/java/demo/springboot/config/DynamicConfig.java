package demo.springboot.config;

import demo.springboot.controller.RocketController;
import demo.springboot.domian.RocketConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LILUO
 * @date 2021/02/14
 */
@Slf4j
@AllArgsConstructor
@Configuration
public class DynamicConfig implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("init rocket consumer begin ...");
        List<RocketConfig> list = new ArrayList(){{
            add(new RocketConfig("consumer-group-1", RocketController.ROCKET_DESTINATION, "*", MessageModel.CLUSTERING));
            add(new RocketConfig("consumer-group-2", RocketController.ROCKET_DESTINATION, "*", MessageModel.CLUSTERING));
        }};
        for (RocketConfig rocketConfig : list) {
            RocketUtil.createConsumer(rocketConfig);
        }
        log.info("init rocket consumer end...");
    }
}
