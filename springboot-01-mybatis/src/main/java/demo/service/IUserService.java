package demo.service;


import demo.domain.UserDO;

/**
 * @author LILUO
 * @date 2018/5/9
 */
public interface IUserService {

     /**
      * 根据id获取用户信息
      * @param userId
      * @return
      */
     UserDO getUserById(Long userId);
}
