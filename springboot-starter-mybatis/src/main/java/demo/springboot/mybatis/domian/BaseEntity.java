package demo.springboot.mybatis.domian;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author LILUO
 * @date 2020/02/02
 */
@Data
public class BaseEntity extends AbstractBaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

}
