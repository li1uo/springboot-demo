package demo.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import demo.springboot.domain.ScheduleJob;

/**
 * @author LILUO
 * @date 2018/11/14
 */
public interface ITaskService extends IService<ScheduleJob> {

    /**
     * 新增task
     *
     * @param scheduleJobDto
     * @return
     */
    boolean insertTask(ScheduleJob scheduleJobDto);

    /**
     * 修改task
     *
     * @param scheduleJobDto
     * @return
     */
    boolean updateTask(ScheduleJob scheduleJobDto);

    /**
     * 删除任务
     *
     * @param id
     * @return
     */
    boolean deleteTask(Long id);

    /**
     * 修改task状态
     *
     * @param id
     * @param status
     * @return
     */
    boolean updateTaskStatus(Long id, Integer status);
}
