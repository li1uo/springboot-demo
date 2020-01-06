package demo.springboot.core.config.schedule.job;

import demo.springboot.core.config.annotation.TaskMonitor;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * @author LILUO
 * @date 2018/11/14
 */
@Component
public class TestTask extends QuartzJobBean {

    private static Logger logger = LoggerFactory.getLogger(TestTask.class);

    @TaskMonitor
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.debug("=====定时任务2=====");
    }
}
