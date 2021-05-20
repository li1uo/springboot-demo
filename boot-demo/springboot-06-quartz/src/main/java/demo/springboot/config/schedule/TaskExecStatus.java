package demo.springboot.config.schedule;

import lombok.Data;

/**
 * @author LILUO
 * @date 2021/01/15
 */
@Data
public class TaskExecStatus {

    /**
     * 状态(1 成功 0 失败)
     */
    private Integer status;

    /**
     * 失败详情
     */
    private String errorMessage;
}
