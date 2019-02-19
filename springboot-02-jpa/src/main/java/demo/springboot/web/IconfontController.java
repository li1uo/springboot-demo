package demo.springboot.web;

import demo.springboot.domain.IconFontDO;
import demo.springboot.service.IIconFontService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LILUO
 * @date 2018/5/9
 */
@RestController
@RequestMapping(value = "/iconfont")
public class IconfontController {

    public static Logger logger = LoggerFactory.getLogger(IconfontController.class);

    @Autowired
    private IIconFontService iIconFontService;

    /**
     * page - 当前页 从 0 开始
     * 根据分页参数返回一页数据
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public List<IconFontDO> findIconfontByPage(Pageable pageable){
        return iIconFontService.findByPage(pageable).getContent();
    }
}
