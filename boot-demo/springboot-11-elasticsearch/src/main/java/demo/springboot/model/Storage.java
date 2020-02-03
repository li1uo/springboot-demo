package demo.springboot.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author LILUO
 * @date 2020/01/31
 */
@Document(indexName = "storage", type = "_doc")
@Data
public class Storage extends BaseEntity {

    /**
     * 商品Id
     */
    @Id
    private Long id;

    /**
     * 商户id
     */
    private Long merchantId;

    /**
     * 商户名
     */
    private String merchantName;

    /**
     * 商品名称
     */
    private String goodName;

    /**
     * 库存数量
     */
    private Long count;

    /**
     * 销量
     */
    private Long sales;

    /**
     * 备注
     */
    private String remark;
}
