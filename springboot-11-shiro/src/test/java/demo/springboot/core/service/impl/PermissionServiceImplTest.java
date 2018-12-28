package demo.springboot.core.service.impl;

import demo.springboot.core.ShiroApplicationTests;
import demo.springboot.core.service.IPermissionService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author LILUO
 * @date 2018/7/4
 */
public class PermissionServiceImplTest extends ShiroApplicationTests {

    @Autowired
    private IPermissionService permissionService;

    @Test
    public void findPermissions() {
        permissionService.findPermissions("liluo");
    }
}