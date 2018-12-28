package demo.springboot.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 定时任务,此处用来打印socket连接的用户信息
 * @author LILUO
 * @date 2018/7/3
 */
@EnableScheduling
@Component
public class ScanTask {

    @Autowired
    private SimpUserRegistry simpUserRegistry;

    /**
     * 打印当前用户信息
     */
    @Scheduled(cron = "0/3 * * * * ? ")
    public void printCurrentUserInfo(){
        System.out.println("=========定时任务==========  当前用户数量: " + simpUserRegistry.getUserCount());
        Set<SimpUser> userSet = simpUserRegistry.getUsers();
        if (userSet.size() > 0){
            userSet.forEach(simpUser -> System.out.println("=========定时任务==========  当前用户名: " + simpUser.getName()));
        }
        System.out.println("=========定时任务==========");
        System.out.println("\n");
    }
}
