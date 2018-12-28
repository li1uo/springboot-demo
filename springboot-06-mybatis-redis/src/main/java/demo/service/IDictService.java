package demo.service;

import demo.domain.DictDO;

/**
 * @author LILUO
 * @date 2018/5/10
 */
public interface IDictService {

    /**
     * 插入数据
     * @param dictDO
     */
    void insertDict(DictDO dictDO);

    /**
     * 根据ID查询
     * @param dictId
     * @return
     */
    DictDO getDictById(Long dictId);

    /**
     * 更新数据
     * @param dictDO
     */
    void updateDict(DictDO dictDO);

    /**
     * 删除数据
     * @param dictId
     */
    void deleteDict(Long dictId);
}
