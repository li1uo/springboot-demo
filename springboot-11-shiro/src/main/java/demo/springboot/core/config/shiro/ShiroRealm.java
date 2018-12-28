package demo.springboot.core.config.shiro;

import demo.springboot.core.domain.vo.UserVO;
import demo.springboot.core.service.IPermissionService;
import demo.springboot.core.service.IRoleService;
import demo.springboot.core.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Set;


/**
 * @author LILUO
 * @date 2018/6/28
 */
@Component
public class ShiroRealm extends AuthorizingRealm {

    @Lazy
    @Autowired
    private IUserService userService;

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IRoleService roleService;

    /**
     * 角色、权限验证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 当前登陆用户的用户名
        String userName = principalCollection.getPrimaryPrincipal().toString();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 角色信息
        Set<String> roleSet = roleService.listRoleByUserName(userName);
        // 权限信息
        Set<String> permissionSet = permissionService.findPermissions(userName);
        info.setRoles(roleSet);
        info.setStringPermissions(permissionSet);
        return info;
    }

    /**
     * 登陆验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取用户用户名
        String userName = authenticationToken.getPrincipal().toString();
        // 获取用户名的用户信息
        UserVO loginUser = userService.getUserByName(userName);
        // 用户不存在
        if (loginUser != null){
            // 将查到的用户账号和密码存放到authenricationInfo 用于后面的权限判断
            // 第三个参数传入随意
            AuthenticationInfo info = new SimpleAuthenticationInfo(loginUser.getUserName(),loginUser.getUserPassword(),getName());
            return info;
        }
        return null;
    }
}
