package demo.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 数据字典
 *
 * @author LILUO
 * @date 2018/5/10
 */
@Data
@TableName("system_dict")
public class DictDO {

    /**
     * ID
     */
    private Long dictId;

    /**
     * 名称
     */
    private String dictName;

    /**
     * 类型
     */
    private String dictType;

}
