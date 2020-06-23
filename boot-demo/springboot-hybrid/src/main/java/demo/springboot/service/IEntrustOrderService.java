package demo.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import demo.springboot.domain.EntrustOrder;

/**
 * @author LILUO
 * @date 2020/06/23
 */
public interface IEntrustOrderService extends IService<EntrustOrder> {

    /**
     * 创建订单
     *
     * @param order
     * @return
     */
    boolean createOrder(EntrustOrder order);
}
