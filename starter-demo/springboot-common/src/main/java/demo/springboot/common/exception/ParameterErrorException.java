package demo.springboot.common.exception;

import demo.springboot.common.domain.IResultCode;

/**
 * 参数不合法异常
 *
 * @author LILUO
 * @date 2018/9/19
 */
public class ParameterErrorException extends RuntimeException {

    private static final long serialVersionUID = 4806373619921798519L;

    /**
     * 信息码
     */
    private int code;

    /**
     * 异常信息
     */
    public String message;

    public ParameterErrorException(String message) {
        this.message = message;
    }

    public ParameterErrorException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ParameterErrorException(IResultCode resultCode) {
        this.code = resultCode.getCode();
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
