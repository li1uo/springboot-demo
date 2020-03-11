package demo.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.scheduling.annotation.Scheduled;


/**
 *
 * @author LILUO
 * @date 2020/02/02
 */
@Slf4j
@Configuration
public class TaskConfig {

    @Autowired
    private SimpUserRegistry simpUserRegistry;

    /**
     * 监控当前用户
     */
    @Scheduled(cron = "0/3 * * * * ? ")
    public void monitorUser(){
        log.debug("current USER count: {}", simpUserRegistry.getUserCount());
    }
}
