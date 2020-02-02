package demo.web;

import demo.domain.vo.MessageVO;
import demo.service.IDictService;
import demo.service.IUserService;
import demo.springboot.common.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LILUO
 * @date 2018/5/10
 */
@RestController
public class IndexController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IDictService dictService;

    @GetMapping(value = "/user/{userId}")
    public Result<MessageVO> index(@PathVariable Long userId){
        MessageVO messageVO = new MessageVO();
        messageVO.setUserDO(userService.getUserById(userId));
        messageVO.setDictDOList(dictService.listDict());
        return Result.data(messageVO);
    }
}
