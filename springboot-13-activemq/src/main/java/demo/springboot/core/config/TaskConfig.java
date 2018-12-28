package demo.springboot.core.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



/**
 * @author LILUO
 * @date 2018/7/4
 */
@EnableScheduling
@Component
public class TaskConfig {

    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * 发送信息到activemq
     */
    @Scheduled(cron = "0/3 * * * * ? ")
    public void sendMsg(){
        System.out.println("==============开始向activemq发送数据============");
        // 与设置的默认queue或者topic有关
        jmsTemplate.convertAndSend("springboot","1111");
        System.out.println("==============发送完毕============");
        System.out.print("\n");
    }

    /**
     * 发送信息到activemq
     */
    @Scheduled(cron = "0/3 * * * * ? ")
    public void sendMsg2(){
        System.out.println("==============开始向activemq发送数据============");
        // 与设置的默认queue或者topic有关
        jmsTemplate.convertAndSend("springboot","2222");
        System.out.println("==============发送完毕============");
        System.out.print("\n");
    }

    /**
     * 发送信息到activemq
     */
    @Scheduled(cron = "0/3 * * * * ? ")
    public void sendMsg3(){
        System.out.println("==============开始向activemq发送数据============");
        // 与设置的默认queue或者topic有关
        jmsTemplate.convertAndSend("springboot","3333");
        System.out.println("==============发送完毕============");
        System.out.print("\n");
    }
}
