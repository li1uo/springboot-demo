package demo.springboot.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import demo.springboot.domain.LogPageDto;
import demo.springboot.domain.ScheduleLog;
import demo.springboot.mapper.LogMapper;
import demo.springboot.service.ILogService;
import org.springframework.stereotype.Service;

/**
 * @author LILUO
 * @date 2021/01/27
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, ScheduleLog> implements ILogService {

    @Override
    public IPage<ScheduleLog> page(IPage page, LogPageDto logPageDto) {
        return page.setRecords(baseMapper.page(page, logPageDto));
    }
}
