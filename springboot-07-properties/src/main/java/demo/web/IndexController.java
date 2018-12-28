package demo.web;

import demo.properties.ChooseProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LILUO
 * @date 2018/5/11
 */
@RestController
public class IndexController {

    public static final Logger log = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private ChooseProperties chooseProperties;

    @Autowired
    private Environment environment;

    @Value("${choose.name}")
    private String name;
    
    @RequestMapping(value = "/")
    public String index(){
        // 从环境中直接拿出属性值
        log.info(environment.getProperty("choose.name"));
        log.info(name);
        return chooseProperties.toString();
    }
}
