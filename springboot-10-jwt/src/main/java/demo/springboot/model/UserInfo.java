package demo.springboot.model;

import lombok.Data;

/**
 * @author LILUO
 * @date 2020/01/07
 */
@Data
public class UserInfo {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 角色id(,分割)
     */
    private String roleId;

    /**
     * 角色集合(,分割)
     */
    private String roleName;
}
