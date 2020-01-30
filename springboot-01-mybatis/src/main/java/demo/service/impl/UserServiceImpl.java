package demo.service.impl;

import demo.domain.UserDO;
import demo.mapper.UserMapper;
import demo.service.IUserService;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Service("userService")
public class UserServiceImpl implements IUserService {

    private UserMapper userMapper;

    @Override
    public UserDO getUserById(Long userId) {
        return userMapper.getUserById(userId);
    }
}
