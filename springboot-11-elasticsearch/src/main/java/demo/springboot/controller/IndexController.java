package demo.springboot.controller;

import demo.springboot.common.domain.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LILUO
 * @date 2020/01/30
 */
@Slf4j
@AllArgsConstructor
@RestController
public class IndexController {

    private ElasticsearchTemplate elasticsearchTemplate;

    @GetMapping("/index")
    public Result index(){
        return Result.success();
    }

    @GetMapping("/list")
    public List list(){
        return new ArrayList();
    }
}
