package demo.springboot.config;

import demo.springboot.domain.AbstractMessageDto;
import lombok.extern.slf4j.Slf4j;
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
     * @param messageDto
     */
    @MessageMapping(value = "/send")
    public void receiveMessage(AbstractMessageDto messageDto){

        log.info("接收到了信息" + messageDto.getMsg());
        Set<SimpUser> set = simpUserRegistry.getUsers();
        log.debug("userCount: {}", simpUserRegistry.getUserCount());

        // sendToUser 目标地址最后还是会转换成/user/liluo/queue/msg
        messagingTemplate.convertAndSendToUser("liluo","/queue/msg", new AbstractMessageDto("你发送的消息为:" + messageDto.getMsg()));
        //return new ServerMessageVO("你发送的消息为:" + message.getName());
    }

    /**
     * 当前端socketjs订阅时触发
     * @return
     */
    @SubscribeMapping("/topic/subscribe")
    public AbstractMessageDto subTopic() {
        log.info("XXX用户订阅了我。。。");
        return new AbstractMessageDto("感谢你订阅了我。。。");
    }

    @SubscribeMapping("/user/queue/msg")
    public AbstractMessageDto onSub() {
        log.info("XXX用户订阅了我。。。");
        System.out.println("订阅成功=== " + simpUserRegistry.getUserCount());
        return new AbstractMessageDto("感谢你订阅了我。。。");
    }
}
