package demo.springboot.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import demo.springboot.mybatis.domian.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author LILUO
 * @date 2020/06/23
 */
@ApiModel(description = "撮合订单")
@TableName("t_match_order")
@Data
public class MatchOrder extends BaseEntity {

    @ApiModelProperty("买单号")
    private String buyOrderNo;

    @ApiModelProperty("卖单号")
    private String sellOrderNo;

    @ApiModelProperty("买家用户id")
    private Long buyUserId;

    @ApiModelProperty("卖家用户id")
    private Long sellUserId;

    @ApiModelProperty("成交数量")
    private BigDecimal amount;

    @ApiModelProperty("成交价格")
    private BigDecimal price;
}
