package demo.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import demo.springboot.domain.LogPageDto;
import demo.springboot.domain.ScheduleLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LILUO
 * @date 2021/01/27
 */
public interface LogMapper extends BaseMapper<ScheduleLog> {

    /**
     * 分页查询
     *
     * @param page
     * @param pageDto
     * @return
     */
    List<ScheduleLog> page(IPage<ScheduleLog> page, @Param("pageDto") LogPageDto pageDto);
}
