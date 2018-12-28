package demo.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * iconfont数据库实体类
 * data jpa默认使用驼峰命名转换
 * @author LILUO
 * @date 2018/5/9
 */
@Table(name = "t_icon_font")
@Entity
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class IconFontDO implements Serializable{
    /**
     * Id
     */
    @Id
    @GeneratedValue
    private Long iconId;
    /**
     *名称
     */
    private String iconName;
    /**
     * 编码
     */
    private String iconCode;
    /**
     * 备注
     */
    private String iconRemark;

    public Long getIconId() {
        return iconId;
    }

    public void setIconId(Long iconId) {
        this.iconId = iconId;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public String getIconCode() {
        return iconCode;
    }

    public void setIconCode(String iconCode) {
        this.iconCode = iconCode;
    }

    public String getIconRemark() {
        return iconRemark;
    }

    public void setIconRemark(String iconRemark) {
        this.iconRemark = iconRemark;
    }

    @Override
    public String toString() {
        return "IconFontDO{" +
                "iconId=" + iconId +
                ", iconName='" + iconName + '\'' +
                ", iconCode='" + iconCode + '\'' +
                ", iconRemark='" + iconRemark + '\'' +
                '}';
    }
}
