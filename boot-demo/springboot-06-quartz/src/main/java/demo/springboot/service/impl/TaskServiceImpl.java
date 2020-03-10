package demo.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import demo.springboot.domain.ScheduleJob;
import demo.springboot.mapper.TaskMapper;
import demo.springboot.service.ITaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author LILUO
 * @date 2018/11/14
 */
@AllArgsConstructor
@Service("taskService")
public class TaskServiceImpl extends ServiceImpl<TaskMapper, ScheduleJob> implements ITaskService {

    private TaskMapper taskMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertTask(ScheduleJob scheduleJobDto) {
        taskMapper.insert(scheduleJobDto);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateTask(ScheduleJob scheduleJobDto) {
        ScheduleJob dto = taskMapper.selectById(scheduleJobDto.getId());
        if (Objects.isNull(dto) || dto.getStatus() == 0){
            return false;
        }
        taskMapper.updateById(scheduleJobDto);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteTask(Long id) {
        ScheduleJob dto = taskMapper.selectById(id);
        if (Objects.isNull(dto) || dto.getStatus() == 0){
            return false;
        }
        dto.setStatus(0);
        taskMapper.updateById(dto);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateTaskStatus(Long id, Integer status) {
        ScheduleJob dto = taskMapper.selectById(id);
        if (Objects.isNull(dto) || dto.getStatus() == 0){
            return false;
        }
        dto.setStatus(status);
        taskMapper.updateById(dto);
        return true;
    }
}
