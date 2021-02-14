package demo.springboot.service;

import demo.springboot.domian.RocketConfig;


/**
 * @author LILUO
 * @date 2021/02/14
 */
public interface IRocketConfigService {

    boolean addConfig(RocketConfig config);

    boolean deleteConfig(String consumerGroup, String topic);
}
