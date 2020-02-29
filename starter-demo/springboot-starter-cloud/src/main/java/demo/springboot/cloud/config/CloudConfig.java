package demo.springboot.cloud.config;

import demo.springboot.tool.yml.YmlPropertyLoaderFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author LILUO
 * @date 2020/02/29
 */
@PropertySource(value = "classpath:/cloud-config.yml", factory = YmlPropertyLoaderFactory.class)
@Configuration
public class CloudConfig {
}
