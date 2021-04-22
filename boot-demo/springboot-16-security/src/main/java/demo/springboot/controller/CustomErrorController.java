package demo.springboot.controller;

import demo.springboot.common.domain.Result;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LILUO
 * @date 2021/04/21
 */
@RestController
public class CustomErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public Result handleError(HttpServletRequest request, HttpServletResponse response) {
        return Result.fail(response.getStatus(), (String) request.getAttribute("javax.servlet.error.message"));
    }
}
