package demo.springboot.config.aspect;

import demo.springboot.common.exception.ServiceException;
import demo.springboot.config.annotation.TaskMonitor;
import demo.springboot.config.schedule.*;
import demo.springboot.domain.ScheduleLog;
import demo.springboot.tool.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author LILUO
 * @date 2018/11/14
 */
@Slf4j
@Aspect
@Component
public class TaskMonitorAspect {

    @Around(value = "@annotation(taskMonitor)")
    public Object around(ProceedingJoinPoint point, TaskMonitor taskMonitor) {
        // 获取执行方法(此方法为拿到实现类的方法)
        Signature sig = point.getSignature();
        MethodSignature msig = (MethodSignature) sig;
        Object result = null;
        // 获取执行bean的 JobExecutionContext参数
        Parameter[] parameters = msig.getMethod().getParameters();
        int index = 0;
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].getType().equals(JobExecutionContext.class)) {
                index = i;
            }
        }
        JobExecutionContext jobExecutionContext = (JobExecutionContext) point.getArgs()[index];

        long beginTime = System.currentTimeMillis();
        TypeNoHolder.clearTypeNo();
        TaskExecStatusHolder.clear();
        TaskExecLogHolder.clear();

        String taskId = jobExecutionContext.getJobDetail().getKey().getName();
        TypeNoHolder.setTypeNo(taskId);
        TaskExecLogHolder.info("【{0}】定时任务执行开始", taskId);

        ScheduleLog scheduleLog = new ScheduleLog();
        scheduleLog.setTaskId(taskId);
        try {

            // 执行目标方法
            result = point.proceed();
            scheduleLog.setStatus(1);

        } catch (ServiceException e) {

            TaskExecStatusHolder.invokeFail(e.getMessage());
            scheduleLog.setStatus(1);
            scheduleLog.setErrorMessage(ExceptionUtils.getMessage(e));
        } catch (Throwable e) {
            // 方法执行异常
            String msg = MessageFormat.format("【{0}】定时任务执行失败", taskId);
            log.error(msg, e);
            String error = "【{0}】定时任务执行失败" + ExceptionUtils.getStackTrace(e);
            TaskExecLogHolder.error(error, taskId);

            TaskExecStatusHolder.invokeFail(e.getMessage());

            scheduleLog.setStatus(0);
            scheduleLog.setErrorMessage(ExceptionUtils.getStackTrace(e));
        } finally {

            long endTime = System.currentTimeMillis();

            TaskExecStatus execStatus = TaskExecStatusHolder.get();
            if (Objects.nonNull(execStatus) && execStatus.getStatus() == 1) {
                scheduleLog.setExecStatus(1);
            }

            log.info(TaskExecLogHolder.get().toString());
            log.info("【{}】执行完毕, cost: {} ms", taskId, endTime - beginTime);
            scheduleLog.setExecTime(endTime - beginTime);
            scheduleLog.setExecDetail(TaskExecLogHolder.get().toString());
            scheduleLog.setCreateTime(new Date());

            TypeNoHolder.clearTypeNo();
            TaskExecStatusHolder.clear();
            TaskExecLogHolder.clear();

            SpringUtil.publishEvent(new ScheduleExecEvent(scheduleLog));
        }

        return result;
    }
}
