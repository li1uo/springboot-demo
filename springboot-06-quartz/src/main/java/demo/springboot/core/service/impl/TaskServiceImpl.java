package demo.springboot.core.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import demo.springboot.core.config.annotation.TaskMonitor;
import demo.springboot.core.domain.ScheduleJobDto;
import demo.springboot.core.exception.ServiceException;
import demo.springboot.core.mapper.TaskMapper;
import demo.springboot.core.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author LILUO
 * @date 2018/11/14
 */
@Service("taskService")
public class TaskServiceImpl implements ITaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public ScheduleJobDto getTaskById(Long taskId) throws ServiceException {
        return taskMapper.selectById(taskId);
    }

    @Override
    public List<ScheduleJobDto> listTask() throws ServiceException {
        return taskMapper.selectList(new EntityWrapper<>());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertTask(ScheduleJobDto scheduleJobDto) throws ServiceException {
        taskMapper.insert(scheduleJobDto);
        return 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateTask(ScheduleJobDto scheduleJobDto) throws ServiceException {
        ScheduleJobDto dto = taskMapper.selectById(scheduleJobDto.getTaskId());
        if (dto == null || dto.getStatus() == 0){
            return 1;
        }
        taskMapper.updateById(scheduleJobDto);
        return 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteTask(Long taskId) throws ServiceException {
        ScheduleJobDto dto = taskMapper.selectById(taskId);
        if (dto == null || dto.getStatus() == 0){
            return 1;
        }
        dto.setStatus(0);
        taskMapper.updateById(dto);
        return 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateTaskStatus(Long taskId, Integer status) throws ServiceException {
        ScheduleJobDto dto = taskMapper.selectById(taskId);
        if (dto == null || dto.getStatus() == 0){
            return 1;
        }
        dto.setStatus(status);
        taskMapper.updateById(dto);
        return 0;
    }
}
