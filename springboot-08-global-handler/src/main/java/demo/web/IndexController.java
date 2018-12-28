package demo.web;

import demo.result.GlobalErrorInfoEnum;
import demo.result.GlobalErrorInfoException;
import demo.result.ResultBean;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LILUO
 * @date 2018/5/11
 */
@RestController
public class IndexController {

    @RequestMapping(value = "/index")
    public ResultBean find(@RequestParam String userName) throws Exception {
        if (StringUtils.isEmpty(userName)){
            throw new GlobalErrorInfoException(GlobalErrorInfoEnum.PARAMETER_ERROR);
        }else{
            String temp = "111";
            if (temp.equals(userName)){
                throw new Exception();
            }
        }
        return new ResultBean("没毛病");
    }
}
