package demo.springboot.config.schedule;

import org.springframework.context.ApplicationEvent;

/**
 * 定时任务调度事件
 *
 * @author LILUO
 * @date 2020/12/01
 */
public class ScheduleExecEvent extends ApplicationEvent {

    public ScheduleExecEvent(Object source) {
        super(source);
    }
}
