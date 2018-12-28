package demo.springboot.core.controller;


import demo.springboot.core.config.shiro.ShiroPermissionFactory;
import demo.springboot.core.config.shiro.ShiroRealm;
import demo.springboot.core.domain.vo.UserVO;
import demo.springboot.core.service.IPermissionService;
import demo.springboot.core.service.IRoleService;
import demo.springboot.core.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.annotation.*;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.Set;

/**
 * @author LILUO
 * @date 2018/6/28
 */
@Controller
public class IndexController {

    private static final Logger log = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private ShiroPermissionFactory shiroPermissionFactory;

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private ShiroRealm shiroRealm;

    /**
     * 返回主页
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    /**
     * 默认返回主页
     * @return
     */
    @RequestMapping(value = "/")
    public String defaultIndex(){
        return "redirect:/index";
    }

    /**
     * 返回登录页面
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    /**
     * 返回没有权限页面
     * @return
     */
    @RequestMapping(value = "/nopower")
    public String nopower(){
        return "nopower";
    }

    /**
     * 返回测试页面(需要登陆)
     * @return
     */
    @RequiresUser
    @RequestMapping(value = "/test")
    public String test(){
        return "test";
    }

    /**
     * 返回角色页面
     * @return
     */
    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/roleAdmin")
    public String roleAdmin(){
        return "role_admin";
    }

    /**
     * 返回测试角色页面
     * @return
     */
    @RequiresRoles(value = {"test1"})
    @RequestMapping(value = "/roleTest")
    public String roleTest(){
        return "role_test";
    }

    /**
     * 测试刷新过滤链
     * @return
     */
    @RequestMapping(value = "/refresh")
    public String refresh(){
        shiroPermissionFactory.refreshFilterChainDefinition();
        return "redirect:/index";
    }

    /**
     * 刷新缓存
     * @return
     */
    @RequestMapping(value = "/cache/clear")
    public String clearCache(){
        shiroRealm.getAuthorizationCache().clear();
        userService.clearCahce();
        return "redirect:/index";
    }

    /**
     * 重新加载缓存
     * @return
     */
    @RequestMapping(value = "/cache/cache")
    public String cacheAuthrization(){
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String userName = SecurityUtils.getSubject().getPrincipal().toString();
        authorizationInfo.setRoles(roleService.listRoleByUserName(userName));
        authorizationInfo.setStringPermissions(permissionService.findPermissions(userName));
        shiroRealm.getAuthorizationCache().put(userName,authorizationInfo);
        return "redirect:/index";
    }

    /**
     * 获取缓存信息
     * @return
     */
    @RequestMapping(value = "/cache/info")
    public String getCacheInfo(){
        Set<Object> keys = shiroRealm.getAuthorizationCache().keys();
        keys.forEach(o -> log.debug("==================  "+ o.toString()));
        Collection<AuthorizationInfo> list = shiroRealm.getAuthorizationCache().values();
        list.forEach(authorizationInfo -> {
            log.debug("==================  "+ authorizationInfo.toString());
        });
        return "redirect:/index";
    }


    /**
     * 登陆
     * @return
     */
    @RequestMapping(value = "/doLogin")
    public String doLogin(UserVO user){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), new Md5Hash(user.getUserPassword(),ByteSource.Util.bytes(user.getUserName()),1024).toString());
        // 登陆验证
        try{
            subject.login(token);
            UserVO currUser = userService.getUserByName(user.getUserName());
            subject.getSession().setAttribute("uid",currUser.getUserId());
            return "index";
        }catch (AuthenticationException e){
            return "login";
        }
    }
}
