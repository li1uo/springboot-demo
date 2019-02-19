package demo.jpa.service.impl;

import demo.jpa.domain.UserDO;
import demo.jpa.repository.UserRepository;
import demo.jpa.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LILUO
 * @date 2018/5/9
 */
@CacheConfig(cacheNames = "user")
@Service("userService")
public class UserServiceImpl implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository repository;

    @Cacheable(value = "getUserById",key = "T(String).valueOf(#userId)")
    @Override
    public UserDO getUserById(Long userId) {
        return repository.getOne(userId);
    }

    /**
     * 查询 - 插入缓存
     * @return
     */
    @Cacheable(value = "listUser")
    @Override
    public List<UserDO> listUser() {
        return repository.findAll(Sort.by("userId").descending());
    }

    /**
     * 清楚缓存
     * @param userDO
     */
    @CacheEvict(value = {"listUser","getUserById"},allEntries = true)
    @Override
    public void insertUser(UserDO userDO) {
        repository.save(userDO);
    }

    /**
     * 不查询 - 更新缓存
     * @param userDO
     */
    @CachePut(value = "getUserById",key = "T(String).valueOf(#userDO.userId)")
    @Override
    public UserDO updateUser(UserDO userDO) {
        return repository.save(userDO);
    }
}
