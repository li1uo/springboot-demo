package demo.springboot.service;

import demo.springboot.config.RocketUtil;
import demo.springboot.domian.RocketConfig;
import org.springframework.stereotype.Service;


/**
 * @author LILUO
 * @date 2021/02/14
 */
@Service
public class RocketConfigServiceImpl implements IRocketConfigService {

    @Override
    public boolean addConfig(RocketConfig config) {
        RocketUtil.createConsumer(config);
        return true;
    }

    @Override
    public boolean deleteConfig(String consumerGroup, String topic, String tag) {

        String name = String.format("%s_%s_%s", consumerGroup, topic, tag);
        RocketUtil.close(name);
        return true;
    }
}
