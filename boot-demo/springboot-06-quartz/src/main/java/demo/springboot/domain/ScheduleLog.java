package demo.springboot.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

/**
 * @author LILUO
 * @date 2020/11/30
 */
@ApiModel("定时任务日志表")
@TableName("schedule_log")
@Data
public class ScheduleLog {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 任务编号
     */
    private String taskId;

    /**
     * 定时任务执行状态(1 成功 0 失败)
     */
    private Integer status;

    /**
     * 数据同步情况(1 已同步数据 0 未同步数据)
     */
    private Integer execStatus = 0;

    /**
     * 详细日志
     */
    private String execDetail;

    /**
     * 异常日志
     */
    private String errorMessage;

    /**
     * 执行花费时间(毫秒)
     */
    private Long execTime;

    /**
     * 执行时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 影响行数
     */
    private Integer affectRow = 0;
}
