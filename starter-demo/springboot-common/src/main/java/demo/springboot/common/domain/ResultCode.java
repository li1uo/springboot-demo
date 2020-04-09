package demo.springboot.common.domain;

import lombok.Getter;

/**
 * 响应状态
 *
 * @author LILUO
 * @date 2020/01/30
 */
@Getter
public enum ResultCode implements IResultCode {

    /**
     * 成功
     */
    SUCCESS(200, "success"),

    /**
     * 参数错误
     */
    PARAMETER_ERROR(400, "parameter error"),

    /**
     * 服务器错误
     */
    SERVICE_ERROR(500, "service error"),

    /**
     * 乐观锁更新失败
     */
    UPDATE_DATA_FAIL(5000, "服务器繁忙"),

    /**
     * 数量不足
     */
    AMOUNT_NOT_ENOUGH(10001, "数量不足");

    final int code;

    final String message;

    ResultCode(int code, String message){
        this.code = code;
        this.message = message;
    }
}
