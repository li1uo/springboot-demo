package demo.result;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LILUO
 * @date 2018/5/11
 */
@RestControllerAdvice
public class GlobalErrorInfoHandler {

    @ExceptionHandler(value = Exception.class)
    public ResultBean errorHandlerOverJson(Exception exception){
        // 如果是自定义异常
        if (exception instanceof GlobalErrorInfoException){
            GlobalErrorInfoException globalErrorInfoException = (GlobalErrorInfoException)exception;
            ErrorInfoInterface errorInfoInterface = globalErrorInfoException.getErrorInfoInterface();
            return new ResultBean(errorInfoInterface);
        }
        return new ResultBean(GlobalErrorInfoEnum.SERVER_ERROR);
    }
}
