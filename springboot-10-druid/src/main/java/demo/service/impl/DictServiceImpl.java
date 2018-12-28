package demo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import demo.domain.DictDO;
import demo.domain.dto.DictDto;
import demo.mapper.DictMapper;
import demo.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author LILUO
 * @date 2018/5/12
 */
@Service("dictService")
public class DictServiceImpl implements IDictService {

    @Autowired
    private DictMapper dictMapper;

    @Override
    public DictDO getDictById(Long dictId) {
        return dictMapper.selectById(dictId);
    }

    @Override
    public List<DictDO> listDict() {
        return dictMapper.selectList(new EntityWrapper<>());
    }

    @Override
    public List<DictDO> pageDict(DictDto dictDto) {
        return dictMapper.selectPage(new Page<DictDO>(dictDto.getPageNumber(),dictDto.getPageSize()),
                new EntityWrapper<>());
    }

    @Override
    public Page<DictDO> paginationDict(Page<DictDO> page, DictDto dictDto) {
        List<DictDO> list = dictMapper.paginationDict(page,dictDto);
        return page.setRecords(list);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void insertDict(DictDto dictDto) throws Exception {
        dictMapper.insert(dictDto);
        throw new Exception();
    }

}
