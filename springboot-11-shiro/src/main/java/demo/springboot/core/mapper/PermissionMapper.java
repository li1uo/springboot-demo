package demo.springboot.core.mapper;

import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author LILUO
 * @date 2018/6/29
 */
@Repository
public interface PermissionMapper {
    /**
     * 根据用户名获取权限名列表
     * @param userName
     * @return
     */
    Set<String> findPermissions(String userName);
}
