package demo.springboot.domian;

import com.baomidou.mybatisplus.annotation.TableName;
import demo.springboot.mybatis.domian.BaseEntity;
import lombok.Data;

/**
 * @author LILUO
 * @date 2020/02/02
 */
@Data
@TableName("system_user")
public class UserDO extends BaseEntity {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPassword;

}
