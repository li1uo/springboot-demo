package demo.springboot.config;

import demo.springboot.common.domain.Result;
import demo.springboot.common.domain.ResultCode;
import demo.springboot.common.exception.ParameterErrorException;
import demo.springboot.common.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


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

    /**
     * 统一异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
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
    @ResponseStatus(HttpStatus.OK)
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
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ServiceException.class)
    public @ResponseBody Result serviceException(ServiceException e){

        return Result.fail(e.getCode(), e.getMessage());
    }

    /**
     * spring security 用户名密码错误
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BadCredentialsException.class)
    public @ResponseBody Result badCredentialsException(BadCredentialsException e){

        return Result.fail(ResultCode.USERNAME_PASSWORD_ERROR);
    }

    /**
     * spring security 没有权限异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(AccessDeniedException.class)
    public @ResponseBody Result accessDeniedException(AccessDeniedException e){

        return Result.fail(ResultCode.NO_PERMISSION_EXCEPTION);
    }
}
