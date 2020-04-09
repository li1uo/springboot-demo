package demo.springboot.mybatis.domian;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author LILUO
 * @date 2020/02/03
 */
@Data
public class AbstractBaseEntity implements Serializable {

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime modifyTime;

    /**
     * 状态(0 正常 1 删除)
     */
    @TableField(exist = false)
    @TableLogic
    private Integer isDeleted;

    /**
     * 版本号
     */
    @Version
    private Long version;
}
