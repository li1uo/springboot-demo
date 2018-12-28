package demo.service;

import com.baomidou.mybatisplus.plugins.Page;
import demo.domain.DictDO;
import demo.domain.dto.DictDto;

import java.util.List;

/**
 * @author LILUO
 * @date 2018/5/12
 */
public interface IDictService {
    /**
     * 根据ID获取数据
     * @param dictId
     * @return
     */
    DictDO getDictById(Long dictId);

    /**
     * 获取数据list
     * @return
     */
    List<DictDO> listDict();

    /**
     * 获取一页数据
     * @param dictDto
     * @return
     */
    List<DictDO> pageDict(DictDto dictDto);

    /**
     * 获取一页数据
     * @param page
     * @param dictDto
     * @return
     */
    Page<DictDO> paginationDict(Page<DictDO> page, DictDto dictDto);

    /**
     * 新增数据
     * @param dictDto
     * @throws Exception
     */
    void insertDict(DictDto dictDto) throws Exception;
}
