package demo.springboot.controller;

import demo.springboot.common.domain.Result;
import demo.springboot.model.Storage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LILUO
 * @date 2020/01/30
 */
@AllArgsConstructor
@Slf4j
@RestController
public class IndexController {

    private ElasticsearchRestTemplate elasticsearchTemplate;

    @GetMapping("/index")
    public Result index(){
        return Result.success();
    }

    @GetMapping("/list")
    public List list(){
        return new ArrayList();
    }

    @PostMapping("/add")
    public Result add(){
        elasticsearchTemplate.createIndex(Storage.class);
        return Result.success();
    }
}
