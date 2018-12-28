package demo.springboot.core.config.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author LILUO
 * @date 2018/6/21
 */
@ControllerAdvice
public class ExceptionAdvice {

    private static Logger log = LoggerFactory.getLogger(ExceptionAdvice.class);

    /**
     * 处理shiro注解 没有权限异常
     * @return
     */
    @ExceptionHandler(value = {AuthorizationException.class})
    public ModelAndView serviceExceptionHandler(Exception e){
        log.info("=============没有权限==============");
        log.info(e.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("nopower");
        return modelAndView;
    }
}
