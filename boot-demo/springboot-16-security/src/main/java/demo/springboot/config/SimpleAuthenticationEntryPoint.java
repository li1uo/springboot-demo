package demo.springboot.config;

import demo.springboot.common.domain.Result;
import demo.springboot.common.domain.ResultCode;
import demo.springboot.tool.util.JsonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author LILUO
 * @date 2021/04/21
 */
public class SimpleAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter printWriter = httpServletResponse.getWriter();
        printWriter.print(JsonUtil.toJson(Result.fail(ResultCode.INVALID_AUTHORIZATION)));
        printWriter.flush();
        printWriter.close();
    }
}
