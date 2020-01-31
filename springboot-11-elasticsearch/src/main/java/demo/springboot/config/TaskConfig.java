package demo.springboot.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author LILUO
 * @date 2020/01/31
 */
@Slf4j
@AllArgsConstructor
@EnableScheduling
@Configuration
public class TaskConfig {

}
