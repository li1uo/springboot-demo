package demo.service;

import demo.domain.DictDO;

import java.util.List;

/**
 * @author LILUO
 * @date 2018/5/10
 */
public interface IDictService {
    /**
     * 字典集合
     * @return
     */
    List<DictDO> listDict();
}
