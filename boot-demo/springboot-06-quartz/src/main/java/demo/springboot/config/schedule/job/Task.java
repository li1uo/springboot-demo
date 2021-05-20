package demo.springboot.config.schedule.job;

import demo.springboot.config.annotation.TaskMonitor;
import demo.springboot.config.schedule.TaskExecLogHolder;
import demo.springboot.config.schedule.TypeNoHolder;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * @author LILUO
 * @date 2018/11/14
 */
@DisallowConcurrentExecution
@Component
public class Task extends QuartzJobBean {

    public static final Logger logger = LoggerFactory.getLogger(Task.class);

    @TaskMonitor
    @Override
    protected void executeInternal(JobExecutionContext context) {
        TaskExecLogHolder.info("【{0}】定时任务......", TypeNoHolder.getTypeNo());
    }
}
