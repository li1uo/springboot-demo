package demo.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;

/**
 *
 * @author LILUO
 * @date 2020/02/02
 */
@Slf4j
@EnableScheduling
@Configuration
public class TaskConfig {

    @Autowired
    private SimpUserRegistry simpUserRegistry;

    /**
     * 打印websocket用户
     */
    @Scheduled(cron = "0/3 * * * * ? ")
    public void printCurrentUserInfo(){
        log.debug("userTask... userCount: {}", simpUserRegistry.getUserCount());
        Set<SimpUser> userSet = simpUserRegistry.getUsers();
        if (userSet.size() > 0){
            userSet.forEach(simpUser -> log.debug("userTask...  userName: {}", simpUser.getName()));
        }
        log.debug("\n");
    }
}
