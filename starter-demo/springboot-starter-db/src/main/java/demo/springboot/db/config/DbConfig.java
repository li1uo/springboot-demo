package demo.springboot.db.config;

import demo.springboot.tool.yml.YmlPropertyLoaderFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author LILUO
 * @date 2020/02/25
 */
@PropertySource(value = "classpath:/db-config.yml", factory = YmlPropertyLoaderFactory.class)
@Configuration
public class DbConfig {

}
