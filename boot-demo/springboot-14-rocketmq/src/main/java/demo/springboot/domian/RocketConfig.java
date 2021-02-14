package demo.springboot.domian;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.rocketmq.spring.annotation.MessageModel;

/**
 * rocket配置类
 *
 * @author LILUO
 * @date 2021/02/14
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RocketConfig {

    private String consumerGroup;

    private String topic;

    private String tag = "*";

    private MessageModel messageModel = MessageModel.CLUSTERING;
}
