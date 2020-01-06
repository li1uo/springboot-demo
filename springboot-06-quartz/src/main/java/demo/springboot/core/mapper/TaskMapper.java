package demo.springboot.core.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import demo.springboot.core.domain.ScheduleJobDto;
import org.springframework.stereotype.Repository;

/**
 * @author LILUO
 * @date 2018/11/14
 */
@Repository
public interface TaskMapper extends BaseMapper<ScheduleJobDto> {

}
