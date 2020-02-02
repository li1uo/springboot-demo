package demo.mapper.master;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import demo.domain.UserDO;

/**
 * @author LILUO
 * @date 2018/5/9
 */
public interface UserMapper extends BaseMapper<UserDO> {

     /**
      * 用户集合
      *
      * @param userId
      * @return
      */
     UserDO getUserById(Long userId);
}
