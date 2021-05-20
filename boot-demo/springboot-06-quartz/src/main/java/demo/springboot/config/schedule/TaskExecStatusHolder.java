package demo.springboot.config.schedule;


/**
 * @author LILUO
 * @date 2021/01/15
 */
public class TaskExecStatusHolder {

    /**
     * 线程本地环境
     */
    private static final ThreadLocal<TaskExecStatus> contextHolder = new ThreadLocal<>();

    public static void invokeSuccess() {
        TaskExecStatus taskExecStatus = new TaskExecStatus();
        taskExecStatus.setStatus(1);
        contextHolder.set(taskExecStatus);
    }

    public static void invokeFail(String errorMessage) {
        TaskExecStatus taskExecStatus = new TaskExecStatus();
        taskExecStatus.setStatus(0);
        taskExecStatus.setErrorMessage(errorMessage);
        contextHolder.set(taskExecStatus);
    }

    public static TaskExecStatus get() {
        return contextHolder.get();
    }

    public static void clear() {
        contextHolder.remove();
    }
}
