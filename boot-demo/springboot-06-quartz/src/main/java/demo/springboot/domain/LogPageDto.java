package demo.springboot.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author LILUO
 * @date 2021/01/27
 */
@Data
public class LogPageDto {

    private String createTime;

    private Date beginTime;

    private Date endTime;

    private Integer status;

    private Integer execStatus;

    private String taskId;
}
