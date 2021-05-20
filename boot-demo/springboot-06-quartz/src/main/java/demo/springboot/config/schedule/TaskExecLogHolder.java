package demo.springboot.config.schedule;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author LILUO
 * @date 2021/01/17
 */
public class TaskExecLogHolder {

    /**
     * 线程本地环境
     */
    private static final ThreadLocal<StringBuilder> contextHolder = new ThreadLocal<>();

    /**
     * threadLocal插入定时任务日志
     *
     * @param logLevel
     * @param log
     */
    public static void append(LogLevel logLevel, String log){
        StringBuilder stringBuilder = contextHolder.get();
        if (Objects.isNull(stringBuilder)) {
            synchronized (TaskExecLogHolder.class) {
                stringBuilder = contextHolder.get();
                if (Objects.isNull(stringBuilder)) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("\n");
                    contextHolder.set(stringBuilder);
                }
            }
        }
        stringBuilder.append(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss:SSS"));
        stringBuilder.append(" ");
        String threadName = Thread.currentThread().getName();
        String currentThreadName = StringUtils.substring(threadName, threadName.length() - 15, threadName.length());
        stringBuilder.append("[");
        stringBuilder.append(StringUtils.leftPad(currentThreadName, 10));
        stringBuilder.append("]");
        stringBuilder.append(" ");

        if (logLevel.equals(LogLevel.ERROR)) {
            stringBuilder.append("【ERROR】");
        } else {
            stringBuilder.append("【 INFO】");
        }

        stringBuilder.append(log);
        stringBuilder.append("\n");
    }

    /**
     * threadLocal插入定时任务日志
     *
     * @param logLevel
     * @param format
     * @param param
     */
    public static void append(LogLevel logLevel, String format, Object ... param){
        String log = MessageFormat.format(format, param);
        append(logLevel, log);
    }

    public static void info(String format, Object ... param) {
        String log = MessageFormat.format(format, param);
        append(LogLevel.INFO, log);
    }

    public static void error(String format, Object ... param) {
        String log = MessageFormat.format(format, param);
        append(LogLevel.ERROR, log);
    }

    public static void clear(){
        contextHolder.remove();
    }

    public static StringBuilder get() {
        return contextHolder.get();
    }
}
