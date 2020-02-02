package demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import demo.domain.UserDO;
import demo.mapper.master.UserMapper;
import demo.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author LILUO
 * @date 2018/5/9
 */
@Slf4j
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDO getUserById(Long userId) {
        return userMapper.getUserById(userId);
    }
}
