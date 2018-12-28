package demo.service.impl;

import demo.domain.DictDO;
import demo.mapper.DictMapper;
import demo.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author LILUO
 * @date 2018/5/10
 */
@Service("dictService")
public class DictServiceImpl implements IDictService{

    @Autowired
    private DictMapper dictMapper;

    @Override
    public void insertDict(DictDO dictDO) {
        dictMapper.insertDict(dictDO);
    }

    @Cacheable(value = "getDictById", key = "T(String).valueOf(#dictId)")
    @Override
    public DictDO getDictById(Long dictId) {
        return dictMapper.getDictById(dictId);
    }

    @CacheEvict(value = "getDictById", key = "T(String).valueOf(#dictDO.dictId)")
    @Override
    public void updateDict(DictDO dictDO) {
        dictMapper.updateDict(dictDO);
    }

    @CacheEvict(value = "getDictById", key = "T(String).valueOf(#dictId)")
    @Override
    public void deleteDict(Long dictId) {
        dictMapper.deleteDict(dictId);
    }
}
