package demo.springboot.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import demo.springboot.core.domain.ScheduleJobDto;
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
public class TaskServiceImpl extends ServiceImpl<TaskMapper, ScheduleJobDto> implements ITaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public ScheduleJobDto getTaskById(Long taskId) {
        return taskMapper.selectById(taskId);
    }

    @Override
    public List<ScheduleJobDto> listTask() {
        return taskMapper.selectList(null);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertTask(ScheduleJobDto scheduleJobDto) {
        return taskMapper.insert(scheduleJobDto);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateTask(ScheduleJobDto scheduleJobDto) {
        ScheduleJobDto dto = taskMapper.selectById(scheduleJobDto.getTaskId());
        if (dto == null || dto.getStatus() == 0){
            return 1;
        }
        taskMapper.updateById(scheduleJobDto);
        return 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteTask(Long taskId) {
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
    public int updateTaskStatus(Long taskId, Integer status) {
        ScheduleJobDto dto = taskMapper.selectById(taskId);
        if (dto == null || dto.getStatus() == 0){
            return 1;
        }
        dto.setStatus(status);
        taskMapper.updateById(dto);
        return 0;
    }
}
