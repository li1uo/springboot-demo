package demo.springboot.core.service;

import java.util.Set;

/**
 * @author LILUO
 * @date 2018/6/29
 */
public interface IRoleService {
    /**
     * 根据用户名查找角色信息
     * @param userName
     * @return
     */
    Set<String> listRoleByUserName(String userName);
}
