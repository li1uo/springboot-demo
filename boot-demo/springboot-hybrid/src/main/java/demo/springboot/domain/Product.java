package demo.springboot.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import demo.springboot.mybatis.domian.BaseEntity;
import lombok.Data;

/**
 * @author LILUO
 * @date 2020/04/09
 */
@TableName("t_product")
@Data
public class Product extends BaseEntity {

    private String productName;

    private Integer amount;
}
