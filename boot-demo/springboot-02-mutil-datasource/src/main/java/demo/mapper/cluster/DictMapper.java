package demo.mapper.cluster;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import demo.domain.DictDO;

import java.util.List;

/**
 * @author LILUO
 * @date 2018/5/10
 */
public interface DictMapper extends BaseMapper<DictDO> {

     /**
      * 字典集合
      *
      * @return
      */
     List<DictDO> listDict();
}
