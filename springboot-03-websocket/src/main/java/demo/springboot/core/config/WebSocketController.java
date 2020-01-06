package demo.springboot.core.config;

import demo.springboot.core.domain.ClientMessageVO;
import demo.springboot.core.domain.ServerMessageVO;
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
@Controller
public class WebSocketController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private SimpUserRegistry simpUserRegistry;

    /**
     * onMessage 前端发送数据时触发
     * 相当于requestMappering
     * @param message
     */
    @MessageMapping(value = "/send")
    public void receiveMessage(ClientMessageVO message){
        logger.info("接收到了信息" + message.getName());
        Set<SimpUser> set = simpUserRegistry.getUsers();
        System.out.println(simpUserRegistry.getUserCount());
        // sendToUser 目标地址最后还是会转换成/user/liluo/queue/msg
        messagingTemplate.convertAndSendToUser("liluo","/queue/msg",new ServerMessageVO("你发送的消息为:" + message.getName()));
        //return new ServerMessageVO("你发送的消息为:" + message.getName());
    }

    /**
     * 当前端socketjs订阅时触发
     * @return
     */
    @SubscribeMapping("/topic/subscribe")
    public ServerMessageVO subTopic() {
        logger.info("XXX用户订阅了我。。。");
        return new ServerMessageVO("感谢你订阅了我。。。");
    }

    @SubscribeMapping("/user/queue/msg")
    public ServerMessageVO onSub() {
        logger.info("XXX用户订阅了我。。。");
        System.out.println("订阅成功=== " + simpUserRegistry.getUserCount());
        return new ServerMessageVO("感谢你订阅了我。。。");
    }
}
