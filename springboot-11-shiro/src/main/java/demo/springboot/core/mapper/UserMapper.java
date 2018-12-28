package demo.springboot.core.mapper;

import demo.springboot.core.domain.vo.UserVO;
import org.springframework.stereotype.Repository;

/**
 * @author LILUO
 * @date 2018/6/29
 */
@Repository
public interface UserMapper {
    /**
     * 根据用户名获取用户信息
     * @param userName
     * @return
     */
    UserVO getUserByName(String userName);
}
