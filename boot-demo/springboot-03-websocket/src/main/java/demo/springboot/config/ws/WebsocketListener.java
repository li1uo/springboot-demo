package demo.springboot.config.ws;

import demo.springboot.domain.MemberConnectedMsg;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

/**
 * @author LILUO
 * @date 2020/06/28
 */
@AllArgsConstructor
@Configuration
public class WebsocketListener {

    private SimpMessagingTemplate messagingTemplate;

    /**
     * 监听新增用户事件
     *
     * @param event
     */
    @EventListener
    public void memberAddListener(SessionConnectedEvent event){
        MemberConnectedMsg msg = new MemberConnectedMsg();
        msg.setMember(event.getUser().getName());
        messagingTemplate.convertAndSend("/topic/member", msg);
    }
}
