package demo.springboot.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 客户端消息
 *
 * @author LILUO
 * @date 2018/7/2
 */
@Data
public class ClientMessageDto implements Serializable {

    private String name;
}
