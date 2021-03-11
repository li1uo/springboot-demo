package demo.springboot.controller;

import demo.springboot.common.domain.Result;
import demo.springboot.tool.util.JsonUtil;
import org.springframework.cloud.gateway.mvc.ProxyExchange;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LILUO
 * @date 2021/03/10
 */
@RequestMapping("/gateway/api")
@RestController
public class GateController {

    @RequestMapping("/proxy")
    public @ResponseBody ResponseEntity<?> proxy(ProxyExchange<byte[]> proxy, @RequestHeader HttpHeaders headers, @RequestBody String param, HttpMethod httpMethod) throws Exception {
        proxy.headers(headers);
        proxy.body(param);
        if (httpMethod == HttpMethod.GET) {
            return proxy.uri("http://localhost:8080/gateway/api/test").get();
        }

        return proxy.uri("http://localhost:8080/gateway/api/test").post();
    }

    @PostMapping("/test")
    public @ResponseBody Result test(HttpServletRequest request, String name){
        System.out.println(name);
        System.out.println(request.getHeader("test-key"));
        return Result.success();
    }
}
