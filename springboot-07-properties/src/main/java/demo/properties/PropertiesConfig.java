package demo.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 使 ChooseProperties 被扫描成bean
 * @author LILUO
 * @date 2018/12/27
 */
@EnableConfigurationProperties(ChooseProperties.class)
@Configuration
public class PropertiesConfig {

}
