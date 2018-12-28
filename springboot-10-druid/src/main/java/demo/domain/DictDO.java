package demo.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * 字典数据库实体类
 * @author LILUO
 * @date 2018/5/10
 */
@TableName("t_basic_dict")
public class DictDO extends Model<DictDO> implements Serializable{

    /**
     * ID
     */
    @TableId(value = "dict_id",type = IdType.AUTO)
    private Long dictId;

    /**
     * 名称
     */
    private String dictName;

    /**
     * 类型
     */
    private String dictType;

    public Long getDictId() {
        return dictId;
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    @Override
    public String toString() {
        return "DictDO{" +
                "dictId=" + dictId +
                ", dictName='" + dictName + '\'' +
                ", dictType='" + dictType + '\'' +
                '}';
    }

    @Override
    protected Serializable pkVal() {
        return this.getDictId();
    }
}
