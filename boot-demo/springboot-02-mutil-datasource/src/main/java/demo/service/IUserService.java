package demo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import demo.domain.UserDO;

/**
 * @author LILUO
 * @date 2018/5/9
 */
public interface IUserService extends IService<UserDO> {

     /**
      * 根据ID获取用户信息
      * @param userId
      * @return
      */
     UserDO getUserById(Long userId);
}
