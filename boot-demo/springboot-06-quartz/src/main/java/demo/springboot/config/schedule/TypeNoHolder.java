package demo.springboot.config.schedule;

import org.springframework.util.Assert;

/**
 * @author LILUO
 * @date 2020/12/11
 */
public class TypeNoHolder {

    /**
     * 线程本地环境
     */
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 设置流程编号
     *
     * @param typeNo
     */
    public static void setTypeNo(String typeNo) {
        clearTypeNo();
        Assert.notNull(typeNo, "typeNo cannot be null");
        contextHolder.set(typeNo);
    }

    /**
     * 获取流程编号
     *
     * @return
     */
    public static String getTypeNo() {
        return contextHolder.get();
    }

    /**
     * 清除流程编号
     */
    public static void clearTypeNo() {
        contextHolder.remove();
    }
}
