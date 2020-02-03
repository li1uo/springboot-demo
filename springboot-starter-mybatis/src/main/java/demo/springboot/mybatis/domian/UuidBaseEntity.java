package demo.springboot.mybatis.domian;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author LILUO
 * @date 2020/02/03
 */
@Data
public class UuidBaseEntity extends AbstractBaseEntity {

    @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

}
