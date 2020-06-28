package demo.springboot.config;

import demo.springboot.domain.ClientMessageDto;
import demo.springboot.domain.SeverMessageDto;
import demo.springboot.domain.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


/**
 * @author LILUO
 * @date 2018/7/2
 */
@Slf4j
@RestController
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * 处理stomp send方法事件
     *
     * @param messageDto
     */
    @MessageMapping(value = "/send")
    @SendTo("/topic/subscribe")
    public SeverMessageDto receiveMessage(UserPrincipal principal, ClientMessageDto messageDto){

        return new SeverMessageDto(messageDto.getMsg(), principal.getName(), LocalDateTime.now());
        // sendToUser 目标地址最后还是会转换成/user/liluo/queue/msg
        // messagingTemplate.convertAndSendToUser(principal.getName(),"/queue/msg", new ClientMessageDto("你发送的消息为:" + messageDto.getMsg()));
    }

    /**
     * stomp subscribe方法触发(订阅公开频道)
     *
     * @return
     */
    @SubscribeMapping("/topic/subscribe")
    public void subTopic() {
        log.info("add user subscribe...");
    }

    /**
     * stomp subscribe方法触发(订阅用户私有频道)
     *
     * @return
     */
    @SubscribeMapping("/user/queue/msg")
    public void onSub() {
        log.info("add user subscribe...");
    }
}
