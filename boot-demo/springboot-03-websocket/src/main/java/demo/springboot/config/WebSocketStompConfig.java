package demo.springboot.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * websocket stomp配置
 *
 * @author LILUO
 * @date 2020/06/27
 */
@AllArgsConstructor
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketStompConfig implements WebSocketMessageBrokerConfigurer {

    private UserInterceptor userInterceptor;

    /**
     * 注册stomp的端点
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 允许使用socketJs方式访问, 访问点为webSocketServer, 允许跨域
        // 在网页上我们就可以通过这个链接 http://localhost:8080/webSocketServer 来和服务器的WebSocket连接
        registry.addEndpoint("/webSocketServer").setAllowedOrigins("*")
                 .setHandshakeHandler(new CustomHandshakeHandler())
                 .addInterceptors(new CustomHandShakeInterceptor())
                 .withSockJS();
    }

    /**
     * 配置信息代理
     *
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 内存代理
        // 订阅Broker名称
        registry.enableSimpleBroker("/queue", "/topic");
        // 全局使用的消息前缀(客户端订阅路径上会体现出来)
        //registry.setApplicationDestinationPrefixes("/app");
        // 点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
        registry.setUserDestinationPrefix("/user");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(userInterceptor);
    }
}
