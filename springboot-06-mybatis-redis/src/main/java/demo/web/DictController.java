package demo.web;

import demo.domain.DictDO;
import demo.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author LILUO
 * @date 2018/5/10
 */
@RestController
public class DictController {

    @Autowired
    private IDictService dictService;

    @RequestMapping(value = "/dict/index")
    public String index(){
        return "主页";
    }

    @PostMapping(value = "/dict/insert")
    public String insertDict(DictDO dictDO){
        dictService.insertDict(dictDO);
        return "插入成功!";
    }

    @PutMapping(value = "/dict/update")
    public String updateDict(DictDO dictDO){
        dictService.updateDict(dictDO);
        return "修改成功!";
    }

    @GetMapping(value = "/dict/{dictId}")
    public DictDO queryDictById(@PathVariable Long dictId){
        return dictService.getDictById(dictId);
    }

    @DeleteMapping(value = "/dict/{dictId}")
    public String deleteDictById(@PathVariable Long dictId){
        dictService.deleteDict(dictId);
        return "删除成功!";
    }
}
