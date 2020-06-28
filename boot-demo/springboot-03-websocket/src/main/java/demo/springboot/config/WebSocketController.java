package demo.springboot.config;

import demo.springboot.domain.AbstractMessageDto;
import demo.springboot.domain.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.RestController;


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
     * stomp send方法触发
     *
     * 与requestMapping同级
     *
     * @param messageDto
     */
    @MessageMapping(value = "/send")
    public void receiveMessage(UserPrincipal principal, AbstractMessageDto messageDto){

        log.info("receive client msg: {}", messageDto.getMsg());

        // sendToUser 目标地址最后还是会转换成/user/liluo/queue/msg
         messagingTemplate.convertAndSendToUser(principal.getName(),"/queue/msg", new AbstractMessageDto("你发送的消息为:" + messageDto.getMsg()));
        //return new AbstractMessageDto("你发送的消息为: " + messageDto.getMsg());
    }

    /**
     * stomp subscribe方法触发(订阅公开频道)
     *
     * @return
     */
    @SubscribeMapping("/topic/subscribe")
    public AbstractMessageDto subTopic() {
        log.info("add user subscribe...");

        return new AbstractMessageDto("感谢你订阅了我。。。");
    }

    /**
     * stomp subscribe方法触发(订阅用户私有频道)
     *
     * @return
     */
    @SubscribeMapping("/user/queue/msg")
    public AbstractMessageDto onSub() {
        log.info("add user subscribe...");

        return new AbstractMessageDto("感谢你订阅了我。。。");
    }
}
