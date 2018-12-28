package demo.web;

import com.baomidou.mybatisplus.plugins.Page;
import demo.domain.DictDO;
import demo.domain.dto.DictDto;
import demo.service.IDictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LILUO
 * @date 2018/5/12
 */
@RestController
@RequestMapping("/dict")
public class DictController {

    private static final Logger log = LoggerFactory.getLogger(DictController.class);

    @Autowired
    private IDictService dictService;

    @GetMapping(value = "/{dictId}")
    public DictDO getDictById(@PathVariable Long dictId){
        return dictService.getDictById(dictId);
    }

    @GetMapping(value = "/list")
    public List<DictDO> listDict(){
        return dictService.listDict();
    }

    /**
     * 获取一页数据(不写sql)
     * @param dictDto
     * @return
     */
    @GetMapping(value = "/page")
    public List<DictDO> pageDict(DictDto dictDto){
        return dictService.pageDict(dictDto);
    }

    /**
     * 获取一页数据(对mapper.xml的sql进行分页)
     * @param dictDto
     * @return
     */
    @GetMapping(value = "/page2")
    public List<DictDO> paginationDict(DictDto dictDto){
        return dictService.paginationDict(new Page<>(dictDto.getPageNumber(),dictDto.getPageSize()),dictDto).getRecords();
    }

    @PostMapping(value = "/create")
    public String insert(DictDto dictDto){
        try {
            dictService.insertDict(dictDto);
        } catch (Exception e) {
            log.info("[服务器出错了]" + e.getStackTrace());
        }
        return "插入成功!";
    }

}
