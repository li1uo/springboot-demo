package demo.springboot.core.domain;



import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author LILUO
 * @date 2018/11/14
 */
@Data
@TableName("t_schedule")
public class ScheduleJobDto implements Serializable {

    @TableId(value = "task_id", type = IdType.AUTO)
    private Long taskId;

    private String className;

    private String cronExpression;

    private String jobName;

    private String jobGroup;

    /**
     * 1正常　0删除　-1暂停
     */
    private Integer status;

    private String description;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;
}
