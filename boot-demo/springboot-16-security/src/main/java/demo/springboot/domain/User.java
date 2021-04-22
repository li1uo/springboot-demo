package demo.springboot.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import demo.springboot.mybatis.domian.BaseEntity;
import lombok.Data;

/**
 * @author LILUO
 * @date 2020/11/02
 */
@TableName("security_user")
@Data
public class User extends BaseEntity {

    private String userName;

    private String password;
}
