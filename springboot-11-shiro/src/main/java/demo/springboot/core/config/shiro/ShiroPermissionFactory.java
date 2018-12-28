package demo.springboot.core.config.shiro;



import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author LILUO
 * @date 2018/6/29
 */
public class ShiroPermissionFactory extends ShiroFilterFactoryBean {

    private static Logger log = LoggerFactory.getLogger(ShiroPermissionFactory.class);

    /**记录静态链**/
    private static ConcurrentHashMap<String,String> staticfilterChainDefinition = new ConcurrentHashMap<>();

    /**
     * 重新生成过滤器链
     * @param filterChainDefinitionMap
     */
    @Override
    public void setFilterChainDefinitionMap(Map<String, String> filterChainDefinitionMap) {

        // 第一次加载时保存静态链
        if (staticfilterChainDefinition.isEmpty()){
            staticfilterChainDefinition.putAll(filterChainDefinitionMap);
        }
        log.debug("=========加载本地权限生成过滤器链=========");
        // 调用默认的设置过滤链
        super.setFilterChainDefinitionMap(filterChainDefinitionMap);
    }

    /**
     * 获取全局静态链
     * @return
     */
    private ConcurrentHashMap<String,String> getStaticfilterChainDefinition(){
        return staticfilterChainDefinition;
    }

    /**
     * 刷新过滤器链
     */
    public void refreshFilterChainDefinition(){
        log.debug("=================刷新过滤链=================");
        setFilterChainDefinitionMap(getStaticfilterChainDefinition());
    }
}
