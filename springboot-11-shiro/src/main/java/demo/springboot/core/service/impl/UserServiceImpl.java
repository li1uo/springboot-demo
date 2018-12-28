package demo.springboot.core.service.impl;

import demo.springboot.core.config.shiro.cache.RedisCache;
import demo.springboot.core.domain.vo.UserVO;
import demo.springboot.core.mapper.UserMapper;
import demo.springboot.core.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author LILUO
 * @date 2018/6/29
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Cacheable(value = "getUserByName",key = "#userName")
    public UserVO getUserByName(String userName) {
        return userMapper.getUserByName(userName);
    }

    @CacheEvict(value = {"getUserByName",RedisCache.KEY_PREFIX},allEntries = true)
    @Override
    public void clearCahce() {

    }
}
