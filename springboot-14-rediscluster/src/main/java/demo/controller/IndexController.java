package demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author LILUO
 * @date 2018/7/23
 */
@RestController
public class IndexController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping(value = "/index")
    public String index(){
        redisTemplate.opsForValue().set(Integer.valueOf(23).toString(), "测试内容", 20, TimeUnit.SECONDS);
        return "测试内容";
    }
}
