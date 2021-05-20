package demo.springboot.config.schedule;

import demo.springboot.domain.ScheduleLog;
import demo.springboot.service.ILogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * @author LILUO
 * @date 2020/12/01
 */
@AllArgsConstructor
@Configuration
@Slf4j
public class ScheduleExecListener {

    private ILogService logService;

    @Async
    @Order
    @EventListener(ScheduleExecEvent.class)
    public void saveApiLog(ScheduleExecEvent event) {
        ScheduleLog scheduleLog = (ScheduleLog) event.getSource();

        logService.save(scheduleLog);
    }
}
