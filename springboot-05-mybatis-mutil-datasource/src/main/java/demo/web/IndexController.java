package demo.web;

import demo.domain.vo.MessageVO;
import demo.service.IDictService;
import demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LILUO
 * @date 2018/5/10
 */
@RestController
@RequestMapping(value = "/index")
public class IndexController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IDictService dictService;

    @GetMapping(value = "/user/{userId}")
    public MessageVO index(@PathVariable Long userId){
        MessageVO messageVO = new MessageVO();
        messageVO.setDictDOList(dictService.listDict());
        messageVO.setUserDO(userService.getUserById(userId));
        return messageVO;
    }
}
