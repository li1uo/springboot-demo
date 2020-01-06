package demo.service.impl;

import demo.domain.UserDO;
import demo.mapper.master.UserMapper;
import demo.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author LILUO
 * @date 2018/5/9
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDO getUserById(Long userId) {
        return userMapper.getUserById(userId);
    }
}
