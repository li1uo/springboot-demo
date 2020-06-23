package demo.springboot.domain;

import lombok.Data;

/**
 * 撮合订单数据
 *
 * @author LILUO
 * @date 2020/06/23
 */
@Data
public class MatchOrderData {

    private String orderNo;

    private Long userId;
}
