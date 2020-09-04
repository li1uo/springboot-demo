package demo.springboot.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.util.Date;

/**
 * @author LILUO
 * @date 2020/09/01
 */
@TableName("share_user")
@Data
public class User {

    @TableId
    private Long id;

    private String userName;

    private Date createTime;

    private Date modifyTime;

    @Version
    private Long version;
}
