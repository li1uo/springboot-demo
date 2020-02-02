package demo.springboot.config;

import demo.springboot.domain.ClientMessageDto;
import demo.springboot.domain.ServerMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;

import java.util.Set;

/**
 * @author LILUO
 * @date 2018/7/2
 */
@Slf4j
@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private SimpUserRegistry simpUserRegistry;

    /**
     * onMessage 前端发送数据时触发
     *
     * 相当于requestMappering
     * @param message
     */
    @MessageMapping(value = "/send")
    public void receiveMessage(ClientMessageDto message){

        log.info("接收到了信息" + message.getName());
        Set<SimpUser> set = simpUserRegistry.getUsers();
        log.debug("userCount: {}", simpUserRegistry.getUserCount());

        // sendToUser 目标地址最后还是会转换成/user/liluo/queue/msg
        messagingTemplate.convertAndSendToUser("liluo","/queue/msg", new ServerMessageDto("你发送的消息为:" + message.getName()));
        //return new ServerMessageVO("你发送的消息为:" + message.getName());
    }

    /**
     * 当前端socketjs订阅时触发
     * @return
     */
    @SubscribeMapping("/topic/subscribe")
    public ServerMessageDto subTopic() {
        log.info("XXX用户订阅了我。。。");
        return new ServerMessageDto("感谢你订阅了我。。。");
    }

    @SubscribeMapping("/user/queue/msg")
    public ServerMessageDto onSub() {
        log.info("XXX用户订阅了我。。。");
        System.out.println("订阅成功=== " + simpUserRegistry.getUserCount());
        return new ServerMessageDto("感谢你订阅了我。。。");
    }
}
