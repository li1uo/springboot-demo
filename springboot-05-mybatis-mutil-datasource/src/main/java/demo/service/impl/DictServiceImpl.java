package demo.service.impl;

import demo.domain.DictDO;
import demo.mapper.cluster.DictMapper;
import demo.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LILUO
 * @date 2018/5/10
 */
@Service("dictService")
public class DictServiceImpl implements IDictService{

    @Autowired
    private DictMapper dictMapper;

    @Override
    public List<DictDO> listDict() {
        return dictMapper.listDict();
    }
}
