package demo.springboot.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import demo.springboot.mybatis.domian.BaseEntity;
import lombok.Data;

/**
 * @author LILUO
 * @date 2020/04/09
 */
@TableName("t_order")
@Data
public class Order extends BaseEntity {

    private Long productId;

    private String orderNo;
}
