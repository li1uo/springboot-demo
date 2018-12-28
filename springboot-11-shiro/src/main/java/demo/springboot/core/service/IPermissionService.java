package demo.springboot.core.service;

import java.util.Set;

/**
 * @author LILUO
 * @date 2018/6/29
 */
public interface IPermissionService {
    /**
     * 根据用户名获取权限名列表
     * @param userName
     * @return
     */
    Set<String> findPermissions(String userName);
}
