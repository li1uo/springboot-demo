package demo.springboot.config.thread;

import demo.springboot.tool.util.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * 定时任务测试线程
 *
 * @author LILUO
 * @date 2020/02/18
 */
@Slf4j
public class TaskThread implements Runnable {

    public int count = 0;

    @Override
    public void run() {
       try{
           count ++;
           //Thread.sleep(1000);
           log.info("date: {}, count: {}", DateTimeUtil.formatDateTime(LocalDateTime.now()), count);
       }catch (Exception e){
           log.error("TaskThread exec error", e);
       }
    }
}
