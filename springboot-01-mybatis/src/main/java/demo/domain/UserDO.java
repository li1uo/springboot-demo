package demo.domain;



import lombok.Data;

import java.io.Serializable;

/**
 * @author LILUO
 * @date 2018/5/9
 */
@Data
public class UserDO implements Serializable {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPassword;

}
