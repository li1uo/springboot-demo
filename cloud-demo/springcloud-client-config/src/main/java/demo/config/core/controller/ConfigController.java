package demo.config.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LILUO
 * @date 2019/02/18
 */
@RestController
public class ConfigController {

    @Autowired
    private ConfigBean configBean;

    @RequestMapping(value = "/config/value")
    public @ResponseBody String getConfigValue(){
        return configBean.getName();
    }
}
