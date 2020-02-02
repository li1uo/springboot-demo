package demo.springboot.core.config.schedule;

import demo.springboot.core.domain.ScheduleJobDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.quartz.*;

/**
 * @author LILUO
 * @date 2018/11/12
 */
public class ScheduleUtil {

    private final static Logger logger = LoggerFactory.getLogger(ScheduleUtil.class);

    /**
     * 获取 Trigger Key
     *
     * @param scheduleJobDto
     * @return
     */
    public static TriggerKey getTriggerKey(ScheduleJobDto scheduleJobDto) {
        return TriggerKey.triggerKey(scheduleJobDto.getJobName(), scheduleJobDto.getJobGroup());
    }

    /**
     * 获取 Job Key
     *
     * @param scheduleJobDto
     * @return
     */
    public static JobKey getJobKey(ScheduleJobDto scheduleJobDto) {
        return JobKey.jobKey(scheduleJobDto.getJobName(), scheduleJobDto.getJobGroup());
    }

    /**
     * 获取 Cron Trigger
     *
     * @param scheduler
     * @param scheduleJobDto
     * @return
     */
    public static CronTrigger getCronTrigger(Scheduler scheduler, ScheduleJobDto scheduleJobDto) throws Exception{
        try {
            return (CronTrigger) scheduler.getTrigger(getTriggerKey(scheduleJobDto));
        } catch (SchedulerException e) {
            throw new Exception("Get Cron trigger failed", e);
        }
    }

    /**
     * 创建任务
     *
     * @param scheduler
     * @param scheduleJobDto
     * @throws Exception
     */
    public static void createScheduleJob(Scheduler scheduler, ScheduleJobDto scheduleJobDto) throws Exception {

        validateCronExpression(scheduleJobDto);

        try {
            // 要执行的 Job 的类
            Class<? extends Job> jobClass = (Class<? extends Job>) Class.forName(scheduleJobDto.getClassName()).newInstance().getClass();

            JobDetail jobDetail = JobBuilder.newJob(jobClass)
                    .withIdentity(scheduleJobDto.getJobName(), scheduleJobDto.getJobGroup())
                    .withDescription(scheduleJobDto.getDescription())
                    .build();

            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJobDto.getCronExpression())
                    .withMisfireHandlingInstructionDoNothing();

            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(scheduleJobDto.getJobName(), scheduleJobDto.getJobGroup())
                    .withDescription(scheduleJobDto.getDescription())
                    .withSchedule(scheduleBuilder)
                    .startNow()
                    .build();

            scheduler.scheduleJob(jobDetail, cronTrigger);

            logger.info("Create schedule job {}-{} success", scheduleJobDto.getJobGroup(), scheduleJobDto.getJobName());

            if (scheduleJobDto.getStatus() == -1) {
                pauseJob(scheduler, scheduleJobDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Execute schedule job failed");
            throw new Exception("Execute schedule job failed", e);
        }
    }


    /**
     * 更新任务
     *
     * @param scheduler
     * @param scheduleJobDto
     * @throws Exception
     */
    public static void updateScheduleJob(Scheduler scheduler, ScheduleJobDto scheduleJobDto) throws Exception {

        validateCronExpression(scheduleJobDto);

        try {

            TriggerKey triggerKey = getTriggerKey(scheduleJobDto);

            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJobDto.getCronExpression())
                    .withMisfireHandlingInstructionDoNothing();

            CronTrigger cronTrigger = getCronTrigger(scheduler, scheduleJobDto);

            cronTrigger = cronTrigger.getTriggerBuilder()
                    .withIdentity(triggerKey)
                    .withDescription(scheduleJobDto.getDescription())
                    .withSchedule(cronScheduleBuilder).build();

            scheduler.rescheduleJob(triggerKey, cronTrigger);

            logger.info("Update schedule job {}-{} success", scheduleJobDto.getJobGroup(), scheduleJobDto.getJobName());

            if (scheduleJobDto.getStatus() == -1) {
                pauseJob(scheduler, scheduleJobDto);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            logger.error("Update schedule job failed");
            throw new Exception("Update schedule job failed", e);
        }
    }

    /**
     * 执行任务
     *
     * @param scheduler
     * @param scheduleJobDto
     * @throws Exception
     */
    public static void run(Scheduler scheduler, ScheduleJobDto scheduleJobDto) throws Exception {
        try {
            scheduler.triggerJob(getJobKey(scheduleJobDto));
            logger.info("Run schedule job {}-{} success", scheduleJobDto.getJobGroup(), scheduleJobDto.getJobName());
        } catch (SchedulerException e) {
            e.printStackTrace();
            logger.error("Run schedule job failed");
            throw new Exception("Run schedule job failed", e);
        }
    }

    /**
     * 暂停任务
     *
     * @param scheduler
     * @param scheduleJobDto
     */
    public static void pauseJob(Scheduler scheduler, ScheduleJobDto scheduleJobDto) throws Exception {
        try {
            scheduler.pauseJob(getJobKey(scheduleJobDto));
            logger.info("Pause schedule job {}-{} success", scheduleJobDto.getJobGroup(), scheduleJobDto.getJobName());
        } catch (SchedulerException e) {
            e.printStackTrace();
            logger.error("Pause schedule job failed");
            throw new Exception("Pause job failed", e);
        }
    }

    /**
     * 继续执行任务
     *
     * @param scheduler
     * @param scheduleJobDto
     * @throws Exception
     */
    public static void resumeJob(Scheduler scheduler, ScheduleJobDto scheduleJobDto) throws Exception {
        try {
            scheduler.resumeJob(getJobKey(scheduleJobDto));
            logger.info("Resume schedule job {}-{} success", scheduleJobDto.getJobGroup(), scheduleJobDto.getJobName());
        } catch (SchedulerException e) {
            e.printStackTrace();
            logger.error("Resume schedule job failed");
            throw new Exception("Resume job failed", e);
        }
    }

    /**
     * 删除任务
     *
     * @param scheduler
     * @param scheduleJobDto
     * @throws Exception
     */
    public static void deleteJob(Scheduler scheduler, ScheduleJobDto scheduleJobDto) throws Exception {
        try {
            scheduler.deleteJob(getJobKey(scheduleJobDto));
            logger.info("Delete schedule job {}-{} success", scheduleJobDto.getJobGroup(), scheduleJobDto.getJobName());
        } catch (SchedulerException e) {
            e.printStackTrace();
            logger.error("Delete schedule job failed");
            throw new Exception("Delete job failed", e);
        }
    }

    /**
     * 校验Cron表达式
     */
    public static void validateCronExpression(ScheduleJobDto scheduleJobDto) throws Exception {
        if (!CronExpression.isValidExpression(scheduleJobDto.getCronExpression())) {
            throw new Exception(String.format("Job %s expression %s is not correct!", scheduleJobDto.getClassName(), scheduleJobDto.getCronExpression()));
        }
    }
}
