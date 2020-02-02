package demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import demo.domain.DictDO;

import java.util.List;

/**
 * @author LILUO
 * @date 2018/5/10
 */
public interface IDictService extends IService<DictDO> {

    /**
     * 字典集合
     * @return
     */
    List<DictDO> listDict();
}
