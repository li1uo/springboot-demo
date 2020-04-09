package demo.springboot.boot.config.exception;

import demo.springboot.common.constant.CommonConstant;
import demo.springboot.common.domain.Result;
import demo.springboot.common.domain.ResultCode;
import demo.springboot.common.exception.ParameterErrorException;
import demo.springboot.common.exception.RateLimitException;
import demo.springboot.common.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理器
 *
 * @author LILUO
 * @date 2018/6/21
 */
@Slf4j
@AllArgsConstructor
@ControllerAdvice
public class ExceptionAdvice {

    private MessageSource messageSource;

    /**
     * 统一异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody Result handleException(Exception e) {
        log.error("service error", e);
        return Result.fail(ResultCode.SERVICE_ERROR);
    }

    /**
     * 处理参数错误异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ParameterErrorException.class)
    public @ResponseBody Result parameterErrorException(ParameterErrorException e){

        return Result.fail(e.getCode(), e.getMessage());
    }

    /**
     * 处理业务异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServiceException.class)
    public @ResponseBody Result serviceException(ServiceException e){

        return Result.fail(e.getCode(), e.getMessage());
    }

    /**
     * 流量限制异常
     *
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(RateLimitException.class)
    public @ResponseBody Result rateLimitException(RateLimitException ex){

        return Result.fail(HttpServletResponse.SC_FORBIDDEN, "Blocked by rate limiter");
    }
}
