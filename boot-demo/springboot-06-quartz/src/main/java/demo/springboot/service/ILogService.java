package demo.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import demo.springboot.domain.LogPageDto;
import demo.springboot.domain.ScheduleLog;

/**
 * @author LILUO
 * @date 2021/01/27
 */
public interface ILogService extends IService<ScheduleLog> {

    /**
     * 分页查询
     *
     * @param page
     * @param logPageDto
     * @return
     */
    IPage<ScheduleLog> page(IPage page, LogPageDto logPageDto);

}
