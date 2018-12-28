package demo.springboot.core.service.impl;

import demo.springboot.core.ShiroApplicationTests;
import demo.springboot.core.service.IRoleService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author LILUO
 * @date 2018/7/4
 */
public class RoleServiceImplTest extends ShiroApplicationTests {

    @Autowired
    private IRoleService roleService;

    @Test
    public void listRoleByUserName() {
        roleService.listRoleByUserName("liluo");
    }
}