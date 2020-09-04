package demo.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import demo.springboot.domain.User;
import demo.springboot.mapper.UserMapper;
import demo.springboot.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author LILUO
 * @date 2020/09/01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
