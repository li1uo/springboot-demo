package demo.springboot.core.service;

import demo.springboot.core.domain.vo.UserVO;

/**
 * @author LILUO
 * @date 2018/6/29
 */
public interface IUserService {
    /**
     * 根据用户名获取用户信息
     * @param userName
     * @return
     */
    UserVO getUserByName(String userName);

    /**
     * 测试 清除缓存
     */
    void clearCahce();
}
