package demo.mapper;

import demo.domain.DictDO;
import org.springframework.stereotype.Repository;


/**
 * @author LILUO
 * @date 2018/5/10
 */
@Repository
public interface DictMapper {

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
