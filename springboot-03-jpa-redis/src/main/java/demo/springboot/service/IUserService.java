package demo.springboot.service;

import demo.springboot.domain.UserDO;

import java.util.List;

/**
 * @author LILUO
 * @date 2018/5/9
 */
public interface IUserService {
    /**
     * 根据ID查询用户信息
     * @param userId
     * @return
     */
    UserDO getUserById(Long userId);

    List<UserDO> listUser();

    void insertUser(UserDO userDO);

    UserDO updateUser(UserDO userDO);
}
