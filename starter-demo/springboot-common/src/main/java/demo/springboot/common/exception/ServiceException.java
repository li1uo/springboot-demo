package demo.springboot.common.exception;

import demo.springboot.common.domain.IResultCode;

/**
 * 服务异常
 *
 * @author LILUO
 * @Date 2018/7/26
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -7758515862715744011L;

    /**
     * 信息码
     */
    private int code;

    /**
     * 异常信息
     */
    public String message;

    public ServiceException(String message) {
        this.message = message;
    }

    public ServiceException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ServiceException(IResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
