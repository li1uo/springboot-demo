package demo.config.core;

import demo.config.core.controller.ConfigBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author LILUO
 * @date 2019/2/18
 */
@EnableConfigurationProperties(ConfigBean.class)
@SpringBootApplication
public class ClientConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientConfigApplication.class, args);
    }

}
