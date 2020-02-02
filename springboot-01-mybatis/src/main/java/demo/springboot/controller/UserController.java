package demo.springboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import demo.springboot.domian.UserDO;
import demo.springboot.domian.UserPageDto;
import demo.springboot.service.IUserService;
import demo.springboot.common.domain.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


/**
 * @author LILUO
 * @date 2020/02/02
 */
@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private IUserService userService;

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<UserDO> getUserById(@PathVariable Long id){
        return Result.data(userService.getById(id));
    }

    /**
     * 根据用户名查询
     *
     * @param userName
     * @return
     */
    @GetMapping("/find")
    public Result<UserDO> findUser(String userName){
        return Result.data(userService.getOne(Wrappers.<UserDO>lambdaQuery().eq(UserDO::getUserName, userName)));
    }

    /**
     * 保存
     *
     * @param userDO
     * @return
     */
    @PostMapping("/save")
    public Result save(UserDO userDO){
        return Result.status(userService.save(userDO));
    }

    /**
     * 修改
     *
     * @param userDO
     * @return
     */
    @PostMapping("/update")
    public Result update(UserDO userDO){
        UserDO current = userService.getById(userDO.getId());
        userDO.setModifyTime(LocalDateTime.now());
        userDO.setVersion(current.getVersion());
        return Result.status(userService.updateById(userDO));
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @PostMapping("/remove/{id}")
    public Result remove(@PathVariable Long id){
        return Result.status(userService.removeById(id));
    }

    /**
     * 分页查询
     *
     * @param userPageDto
     * @return
     */
    @GetMapping("/list")
    public Result<List<UserDO>> list(UserPageDto userPageDto){
        IPage<UserDO> page = userService.listUser(userPageDto.getPage(), userPageDto);
        return Result.data(page.getRecords());
    }
}
