package demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import demo.domain.DictDO;
import demo.domain.dto.DictDto;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author LILUO
 * @date 2018/5/10
 */
@Repository
public interface DictMapper extends BaseMapper<DictDO>{
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

     /**
      * 获取一页数据
      * @param page
      * @param dictDto
      * @return
      */
     List<DictDO> paginationDict(Page<DictDO> page, DictDto dictDto);
}
