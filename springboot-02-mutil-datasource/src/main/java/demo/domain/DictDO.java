package demo.domain;

/**
 * 数据字典
 *
 * @author LILUO
 * @date 2018/5/10
 */
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
}
