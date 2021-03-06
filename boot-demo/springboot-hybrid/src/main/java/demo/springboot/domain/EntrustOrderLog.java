package demo.springboot.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import demo.springboot.mybatis.domian.UuidBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author LILUO
 * @date 2020/06/23
 */
@ApiModel(description = "委托订单记录")
@TableName("t_entrust_order_log")
@Data
public class EntrustOrderLog extends UuidBaseEntity {

    @ApiModelProperty("订单号")
    private String orderNo;

    @ApiModelProperty("操作类型(1 挂单 2 匹配成交 3 取消订单)")
    private Integer type;
}
