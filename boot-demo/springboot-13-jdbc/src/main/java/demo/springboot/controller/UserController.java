package demo.springboot.controller;

import demo.springboot.common.domain.Result;
import demo.springboot.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author LILUO
 * @date 2020/11/18
 */
@AllArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    /**
     * 查询sql
     */
    public static final String QUERY_USER_LIST = "select * from system_user";

    /**
     * sql块
     */
    public static final String SQL_BLOCK = "begin\n" +
            "insert into SYSTEM_USER(id, user_name, user_password) values (1, 'liluo', 'liluo');\n" +
            "insert into SYSTEM_USER(id, user_name, user_password) values (2, 'test', 'test');\n" +
            "end;";

    private JdbcTemplate jdbcTemplate;

    @GetMapping("/list")
    public @ResponseBody List<User> listUser(){
        // 传入class只适用于单列结果
        return jdbcTemplate.queryForList(QUERY_USER_LIST, User.class);
    }

    @GetMapping("/list/map")
    public @ResponseBody List<Map<String, Object>> listUserMap(){
        List<Map<String, Object>> result = jdbcTemplate.queryForList(QUERY_USER_LIST);
        return result;
    }

    @PostMapping("/exec")
    public @ResponseBody Result exec(){
        // 可使用ScriptUtils执行sql脚本文件
        jdbcTemplate.execute(SQL_BLOCK);
        return Result.success();
    }
}
