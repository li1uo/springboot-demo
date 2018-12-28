package demo.mapper.cluster;

import demo.domain.DictDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LILUO
 * @date 2018/5/10
 */
@Repository
public interface DictMapper {
     /**
      * 字典集合
      * @return
      */
     List<DictDO> listDict();
}
