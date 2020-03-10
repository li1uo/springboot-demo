package demo.springboot.controller;

import demo.springboot.config.schedule.ScheduleUtil;
import demo.springboot.domain.ScheduleJob;
import demo.springboot.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author LILUO
 * @date 2018/11/16
 */
@Controller
public class TaskController {

    @Autowired
    private ITaskService taskService;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    /**
     * 查看任务列表
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/index")
    public String list(HttpServletRequest request) {
        // 获取所有task信息
        List<ScheduleJob> list = taskService.list();
        request.setAttribute("taskList", list);
        return "index";
    }

    /**
     * 暂停任务
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/pause/{id}")
    public String pause(@PathVariable Long id) throws Exception {
        // 暂停任务
        taskService.updateTaskStatus(id, -1);
        ScheduleUtil.pauseJob(schedulerFactoryBean.getScheduler(), taskService.getById(id));
        return "redirect:/index";
    }

    /**
     * 恢复任务
     *
     * @param taskId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/resume/{taskId}")
    public String resume(@PathVariable Long taskId) throws Exception {
        // 恢复任务
        taskService.updateTaskStatus(taskId, 1);
        ScheduleUtil.resumeJob(schedulerFactoryBean.getScheduler(), taskService.getById(taskId));
        return "redirect:/index";
    }
}
