package demo.springboot.controller;

import demo.springboot.common.domain.Result;
import demo.springboot.config.lock.ZkLocker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * zk framework Curator测试类
 *
 * @author LILUO
 * @date 2020/02/25
 */
@ConditionalOnProperty(value = "zk.lock.enable", havingValue = "true")
@Slf4j
@RestController
public class CuratorController {

    @Autowired
    private ZkLocker zkLocker;

    private int count = 10;

    @PostMapping("/order")
    public Result order(){
        String path = "order";

        zkLocker.lock(path);
        if (count > 0){
            log.info("第 {} 件商品", count);
            count--;
        }
        //zkLocker.release(path);
        return Result.success();
    }

    @GetMapping("/count")
    public Result count(){
        return Result.data(count);
    }

}
