package demo.springboot.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import demo.springboot.domian.UserDO;
import demo.springboot.domian.UserPageDto;
import demo.springboot.mapper.UserMapper;
import demo.springboot.service.IUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * @author LILUO
 * @date 2020/02/02
 */
@Slf4j
@AllArgsConstructor
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements IUserService {

    private UserMapper userMapper;

    @Override
    public IPage<UserDO> listUser(IPage<UserDO> page, UserPageDto userPageDto) {
        return page.setRecords(userMapper.listUser(page, userPageDto));
    }
}
