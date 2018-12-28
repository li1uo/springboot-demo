package demo.springboot.core.config.shiro;

import demo.springboot.core.config.shiro.cache.RedisCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LILUO
 * @date 2018/6/28
 */
@Configuration
public class ShiroConfiguration {

    @Autowired
    private ShiroRealm shiroRealm;

    @Resource
    private RedisCacheManager shiroCacheManager;

    /**
     * 权限管理，配置主要是Realm的管理认证
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        shiroRealm.setCachingEnabled(true);
        shiroRealm.setAuthenticationCachingEnabled(false);
        shiroRealm.setAuthorizationCachingEnabled(true);
        securityManager.setRealm(shiroRealm);
        securityManager.setCacheManager(shiroCacheManager);
        return securityManager;
    }

    /**
     * Filter工厂，设置对应的过滤条件和跳转条件
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroPermissionFactory shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroPermissionFactory shiroPermissionFactory = new ShiroPermissionFactory();
        shiroPermissionFactory.setSecurityManager(securityManager);
        Map<String,String> map = new HashMap<>(13);
        // 登出
        map.put("/logout","logout");
        // 不需要验证部分
        map.put("/login","anon");
        map.put("/nopower","anon");
        map.put("/doLogin","anon");
        // 需要验证部分
        map.put("/index","authc");
        // 登录
        shiroPermissionFactory.setLoginUrl("/login");
        // 首页
        shiroPermissionFactory.setSuccessUrl("/index");
        // 错误页面，认证不通过跳转
        shiroPermissionFactory.setUnauthorizedUrl("/nopower");
        shiroPermissionFactory.setFilterChainDefinitionMap(map);
        return shiroPermissionFactory;
    }

    /**
     * 加入注解的使用，不加入这个注解不生效
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }
}
