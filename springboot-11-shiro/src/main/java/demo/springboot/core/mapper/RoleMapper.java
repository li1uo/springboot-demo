package demo.springboot.core.mapper;

import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author LILUO
 * @date 2018/6/29
 */
@Repository
public interface RoleMapper {
    /**
     * 根据用户名查找角色信息
     * @param userName
     * @return
     */
    Set<String> listRoleByUserName(String userName);
}
