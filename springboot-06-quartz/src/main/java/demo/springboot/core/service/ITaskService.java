package demo.springboot.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import demo.springboot.core.domain.ScheduleJobDto;

import java.util.List;

/**
 * @author LILUO
 * @date 2018/11/14
 */
public interface ITaskService extends IService<ScheduleJobDto> {

    /**
     * 根据id获取定时任务
     * @param taskId
     * @return
     */
    ScheduleJobDto getTaskById(Long taskId);

    /**
     * 获取所有task
     * @return
     */
    List<ScheduleJobDto> listTask();

    /**
     * 新增task
     * @param scheduleJobDto
     * @return
     */
    int insertTask(ScheduleJobDto scheduleJobDto);

    /**
     * 修改task
     * @param scheduleJobDto
     * @return
     */
    int updateTask(ScheduleJobDto scheduleJobDto);

    /**
     * 删除task
     * @param taskId
     * @return
     */
    int deleteTask(Long taskId);

    /**
     * 修改task状态
     * @param taskId
     * @param status
     * @return
     */
    int updateTaskStatus(Long taskId, Integer status);
}
