package demo.springboot.core.exception;

/**
 * @author LILUO
 * @date 2018/11/12
 */
public class ServiceException extends Exception {

    /**异常信息**/
    private String message;

    private Exception exception;

    public ServiceException(String message) {
        this.message = message;
    }

    public ServiceException(String message, Exception e) {
        this.message = message;
        this.exception = e;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
