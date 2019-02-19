package demo.springboot.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * activemq监听器
 * @author LILUO
 * @date 2018/7/5
 */
@Component
public class ActivemqListener {

    private static Logger logger = LoggerFactory.getLogger(ActivemqListener.class);

    /**
     * jms监听默认实现(无法实现ack手动确认回滚)
     * @param message
     */
    @JmsListener(destination = "springboot-queue")
    public void receiveTopic(String message) {
        System.out.println("==========监听queue==========");
        System.out.println(message);
        System.out.print("\n");
    }

    /**
     * 根据自定义的 containerFactory 来使用手动确认与重新接收
     * @param text
     * @param session
     * @throws JMSException
     */
    @JmsListener(destination = "springboot", containerFactory = "jmsQueueListener")
    public void receiveQueue(final TextMessage text, Session session) throws JMSException {
        try {
            // 使用手动签收模式，需要手动的调用，如果不在catch中调用session.recover()消息只会在重启服务后重发
            String validateName = "1111";
            if (validateName.equals(text.getText())){
                logger.debug("Consumer  首次  收到的报文为:" + text.getText());
                text.acknowledge();
            }else{
                if (text.getJMSRedelivered()) {
                    logger.debug("Consumer  再次  收到的报文为:" + text.getText());
                }
                session.recover();
            }
        } catch (Exception e) {
            // 此不可省略 重发信息使用
            session.recover();
        }
    }
}
