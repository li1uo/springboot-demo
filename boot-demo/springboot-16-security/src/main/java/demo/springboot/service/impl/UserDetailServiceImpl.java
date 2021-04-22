package demo.springboot.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import demo.springboot.domain.JwtUserDetail;
import demo.springboot.domain.User;
import demo.springboot.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

/**
 * 用户登陆校验类
 *
 * @author LILUO
 * @date 2020/11/02
 */
@AllArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserName, s));
        if (Objects.isNull(user)){
            throw new UsernameNotFoundException("用户不存在");
        }
        return new JwtUserDetail(user.getUserName(), user.getPassword(), new ArrayList<>(), user.getId());
    }
}
