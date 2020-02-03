package demo.config.core.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author LILUO
 * @date 2019/02/18
 */
@RefreshScope
@ConfigurationProperties(prefix = "test")
public class ConfigBean {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ConfigBean{" +
                "name='" + name + '\'' +
                '}';
    }
}
