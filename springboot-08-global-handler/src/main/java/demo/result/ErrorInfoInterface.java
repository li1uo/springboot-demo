package demo.result;

/**
 * @author LILUO
 * @date 2018/5/11
 */
public interface ErrorInfoInterface {
    /**
     * 获取错误代码
     * @return
     */
    String getCode();

    /**
     * 获取错误信息
     * @return
     */
    String getMessage();
}
