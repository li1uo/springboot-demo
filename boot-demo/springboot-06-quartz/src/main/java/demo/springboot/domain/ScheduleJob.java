package demo.springboot.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 定时任务
 *
 * @author LILUO
 * @date 2018/11/14
 */
@Data
@TableName("system_schedule")
public class ScheduleJob implements Serializable {

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 执行class名称
     */
    private String className;

    /**
     * 时间表达式
     */
    private String cronExpression;

    /**
     * job名称
     */
    private String jobName;

    /**
     * job group
     */
    private String jobGroup;

    /**
     * 任务状态(1 正常　0 删除　-1 暂停)
     */
    private Integer status;

    /**
     * 任务描述
     */
    private String description;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;
}
