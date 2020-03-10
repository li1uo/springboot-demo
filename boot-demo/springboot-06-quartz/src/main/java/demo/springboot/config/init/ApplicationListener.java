package demo.springboot.config.init;

import demo.springboot.config.schedule.ScheduleUtil;
import demo.springboot.domain.ScheduleJob;
import demo.springboot.service.ITaskService;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.quartz.JobStoreType;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @author LILUO
 * @date 2018/11/14
 */
@Component
public class ApplicationListener implements CommandLineRunner {

    public static Logger logger = LoggerFactory.getLogger(ApplicationListener.class);

    @Autowired
    private Scheduler scheduleBean;

    @Autowired
    private ITaskService taskService;

    @Autowired
    private QuartzProperties quartzProperties;

    @Override
    public void run(String... args) {
        if (quartzProperties.getJobStoreType().equals(JobStoreType.MEMORY)){
            initData();
        }else{
            start();
        }
    }

    /**
     * quartz以内存模式启动时初始化数据
     */
    public void initData(){
        // 应用启动之后执行所有可执行的的任务
        List<ScheduleJob> scheduleJobList = taskService.list();
        for (ScheduleJob scheduleJob : scheduleJobList) {
            try {
                CronTrigger cronTrigger = ScheduleUtil.getCronTrigger(scheduleBean, scheduleJob);
                if (Objects.isNull(cronTrigger)) {
                    ScheduleUtil.createScheduleJob(scheduleBean, scheduleJob);
                } else {
                    ScheduleUtil.updateScheduleJob(scheduleBean, scheduleJob);
                }
                logger.info("Startup {}-{} success", scheduleJob.getJobGroup(), scheduleJob.getJobName());
            } catch (Exception e) {
                logger.error("quartz initData error", e);
            }
        }
    }

    /**
     * 启动quartz
     */
    public void start(){
        try {
            scheduleBean.start();
        } catch (SchedulerException e) {
            logger.error("quartz start error", e);
        }
    }
}
