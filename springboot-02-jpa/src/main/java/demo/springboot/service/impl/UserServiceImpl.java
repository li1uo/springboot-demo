package demo.springboot.service.impl;

import demo.springboot.domain.UserDO;
import demo.springboot.repository.UserRepository;
import demo.springboot.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LILUO
 * @date 2018/5/9
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository repository;

    @Override
    public UserDO getUserById(Long userId) {
        return repository.getOne(userId);
    }

    @Override
    public List<UserDO> listUser() {
        return repository.findAll(Sort.by("userId").descending());
    }
}
