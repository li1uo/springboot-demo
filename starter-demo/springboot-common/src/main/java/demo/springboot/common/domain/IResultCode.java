package demo.springboot.common.domain;

import java.io.Serializable;

/**
 * @author LILUO
 * @date 2020/01/30
 */
public interface IResultCode extends Serializable {

    /**
     * 状态码
     *
     * @return
     */
    int getCode();

    /**
     * 消息
     *
     * @return
     */
    String getMessage();

}
