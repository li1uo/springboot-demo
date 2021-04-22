package demo.springboot.config;

import demo.springboot.common.domain.Result;
import demo.springboot.common.domain.ResultCode;
import demo.springboot.tool.util.JsonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author LILUO
 * @date 2021/04/21
 */
public class CustomerAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        // 返回json形式的错误信息
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setStatus(HttpStatus.OK.value());

        Result result = Result.fail(ResultCode.INVALID_AUTHORIZATION);
        httpServletResponse.getWriter().println(JsonUtil.toJson(result));
        httpServletResponse.getWriter().flush();
    }
}
