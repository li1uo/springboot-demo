package demo.springboot.db.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author LILUO
 * @date 2020/02/25
 */
@PropertySource(value = "classpath:/db-config.yml")
@Configuration
public class DbConfig {

}
