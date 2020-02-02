package demo.springboot.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import demo.springboot.domian.UserDO;
import demo.springboot.domian.UserPageDto;

/**
 * @author LILUO
 * @date 2020/02/02
 */
public interface IUserService extends IService<UserDO> {


    /**
     * 获取一页用户信息
     *
     * @param page
     * @param userPageDto
     * @return
     */
    IPage<UserDO> listUser(IPage<UserDO> page, UserPageDto userPageDto);
}
