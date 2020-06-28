package demo.springboot.config.ws;

import demo.springboot.domain.UserPrincipal;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;

import java.util.LinkedList;
import java.util.Map;

/**
 * 消息处理器
 *
 * @author LILUO
 * @date 2020/06/28
 */
@Deprecated
public class UserInterceptor implements ChannelInterceptor {

    public static final String USER_FLAG = "name";

    /**
     * 消息拦截器, 在向客户端发送消息前进入此方法
     *
     * @param message
     * @param channel
     * @return
     */
    @SuppressWarnings("rawtypes")
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        // 获取stomp connect中的 header, 根据token 设置用户
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            Object raw = message.getHeaders().get(SimpMessageHeaderAccessor.NATIVE_HEADERS);
            if (raw instanceof Map) {
                // 拿到请求头中的token参数 客户端中定义了该参数的user标识
                Object name = ((Map) raw).get(USER_FLAG);
                if (name instanceof LinkedList) {
                    // 设置当前访问器的认证用户
                    accessor.setUser(new UserPrincipal(((LinkedList) name).get(0).toString()));
                }else{
                    accessor.setUser(new UserPrincipal(name.toString()));
                }
            }
        }
        return message;
    }
}
