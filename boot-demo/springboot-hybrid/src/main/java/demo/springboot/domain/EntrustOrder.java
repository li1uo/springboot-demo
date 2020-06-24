package demo.springboot.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import demo.springboot.mybatis.domian.UuidBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author LILUO
 * @date 2020/06/23
 */
@ApiModel(description = "委托订单")
@TableName("t_entrust_order")
@Data
public class EntrustOrder extends UuidBaseEntity {

    @ApiModelProperty("订单号")
    private String orderNo;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("用户类型(1 买方 2 卖方)")
    private Integer userType;

    @ApiModelProperty("数量")
    private BigDecimal amount;

    @ApiModelProperty("剩余数量")
    private BigDecimal remainAmount;

    @ApiModelProperty("价格")
    private BigDecimal price;

    @ApiModelProperty("订单状态(1 未成交 0 已取消 2 部分成交 3 全部成交)")
    private Integer orderStatus;
}
