package demo.springboot.mapper;

import demo.springboot.domain.UserDO;
import org.springframework.stereotype.Repository;

/**
 * @author LILUO
 * @date 2019/08/28
 */
@Repository
public interface User2Mapper {

    Long insertUser(UserDO userDO);
}
