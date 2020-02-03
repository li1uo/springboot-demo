package demo.springboot.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 服务端消息
 *
 * @author LILUO
 * @date 2020/02/02
 */
@Data
public class ServerMessageDto implements Serializable {

    public ServerMessageDto(String msg){
        this.responseMessage = msg;
    }

    private String responseMessage;
}
