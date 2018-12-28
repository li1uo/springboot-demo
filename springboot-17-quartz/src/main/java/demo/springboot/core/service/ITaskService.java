package demo.springboot.core.service;

import demo.springboot.core.domain.ScheduleJobDto;
import demo.springboot.core.exception.ServiceException;

import java.util.List;

/**
 * @author LILUO
 * @date 2018/11/14
 */
public interface ITaskService {

    /**
     * 根据id获取定时任务
     * @param taskId
     * @return
     */
    ScheduleJobDto getTaskById(Long taskId) throws ServiceException;

    /**
     * 获取所有task
     * @return
     */
    List<ScheduleJobDto> listTask() throws ServiceException;

    /**
     * 新增task
     * @param scheduleJobDto
     * @return
     */
    int insertTask(ScheduleJobDto scheduleJobDto) throws ServiceException;

    /**
     * 修改task
     * @param scheduleJobDto
     * @return
     * @throws ServiceException
     */
    int updateTask(ScheduleJobDto scheduleJobDto) throws ServiceException;

    /**
     * 删除task
     * @param taskId
     * @return
     * @throws ServiceException
     */
    int deleteTask(Long taskId) throws ServiceException;

    /**
     * 修改task状态
     * @param taskId
     * @param status
     * @return
     * @throws ServiceException
     */
    int updateTaskStatus(Long taskId, Integer status) throws ServiceException;
}
