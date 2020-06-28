package demo.springboot.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author LILUO
 * @date 2020/06/28
 */
@Data
public class SeverMessageDto extends ClientMessageDto {

    public SeverMessageDto(String msg, String userName, LocalDateTime sendTime){
        this.sendUserName = userName;
        this.sendTime = sendTime;
        setMsg(msg);
    }

    private String sendUserName;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime;
}
