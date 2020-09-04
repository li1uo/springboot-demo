package demo.springboot.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.util.Date;

/**
 * @author LILUO
 * @date 2020/09/04
 */
@TableName("share_order")
@Data
public class Order {

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    private String baseSymbol;

    private String quoteSymbol;

    private String symbol;

    private Date createTime;

    private Date modifyTime;

    @Version
    private Long version;
}
