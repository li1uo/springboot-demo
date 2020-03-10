package demo.springboot.domain;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author LILUO
 * @date 2020/02/02
 */
@Data
public class AbstractMessageDto implements Serializable {

    public AbstractMessageDto(){}

    public AbstractMessageDto(String msg){
        this.msg = msg;
    }

    private String msg;
}
