package demo.springboot.core.service.impl;

import demo.springboot.core.mapper.RoleMapper;
import demo.springboot.core.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author LILUO
 * @date 2018/6/29
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Set<String> listRoleByUserName(String userName) {
        return roleMapper.listRoleByUserName(userName);
    }
}
