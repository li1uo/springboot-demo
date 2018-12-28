package demo.mapper.master;

import demo.domain.UserDO;
import org.springframework.stereotype.Repository;

/**
 * @author LILUO
 * @date 2018/5/9
 */
@Repository
public interface UserMapper {
     /**
      * 用户集合
      * @param userId
      * @return
      */
     UserDO getUserById(Long userId);
}
