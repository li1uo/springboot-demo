package demo.mapper;

import demo.domain.UserDO;
import org.springframework.stereotype.Repository;

/**
 * @author LILUO
 * @date 2018/5/9
 */
@Repository
public interface UserMapper {
     /**
      * 根据id获取用户信息
      * @param userId
      * @return
      */
     UserDO getUserById(Long userId);
}
