package demo.springboot.core.service.impl;

import demo.springboot.core.mapper.PermissionMapper;
import demo.springboot.core.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author LILUO
 * @date 2018/6/29
 */
@Service("permissionService")
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Set<String> findPermissions(String userName) {
        return permissionMapper.findPermissions(userName);
    }
}
