package demo.springboot.core.config.init;

import demo.springboot.core.config.schedule.ScheduleUtil;
import demo.springboot.core.domain.ScheduleJobDto;
import demo.springboot.core.service.ITaskService;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author LILUO
 * @date 2018/11/14
 */
@EnableScheduling
@Component
public class ApplicationListener implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(ApplicationListener.class);

    @Autowired
    private Scheduler scheduleBean;

    @Autowired
    private ITaskService taskService;

    @Override
    public void run(String... args) {
        // 应用启动之后执行所有可执行的的任务
        List<ScheduleJobDto> scheduleJobList = taskService.listTask();
        for (ScheduleJobDto scheduleJob : scheduleJobList) {
            try {
                CronTrigger cronTrigger = ScheduleUtil.getCronTrigger(scheduleBean, scheduleJob);
                if (cronTrigger == null) {
                    ScheduleUtil.createScheduleJob(scheduleBean, scheduleJob);
                } else {
                    ScheduleUtil.updateScheduleJob(scheduleBean, scheduleJob);
                }
                logger.info("Startup {}-{} success", scheduleJob.getJobGroup(), scheduleJob.getJobName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
