package demo.springboot.service.impl;

import demo.springboot.domain.UserDO;
import demo.springboot.mapper.User1Mapper;
import demo.springboot.mapper.User2Mapper;
import demo.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author LILUO
 * @date 2019/08/28
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private User1Mapper user1Mapper;

    @Autowired
    private User2Mapper user2Mapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertUser(UserDO userDO) {
        user1Mapper.insertUser(userDO);
        user2Mapper.insertUser(userDO);
    }
}
