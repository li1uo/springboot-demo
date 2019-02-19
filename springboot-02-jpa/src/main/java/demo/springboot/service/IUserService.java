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

    /**
     * 获取用户信息列表
     * @return
     */
    List<UserDO> listUser();

    /**
     * 新增用户
     * @param userDO
     */
    void insertUser(UserDO userDO);

    /**
     * 更新用户信息
     * @param userDO
     * @return
     */
    UserDO updateUser(UserDO userDO);
}
