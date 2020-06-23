package demo.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import demo.springboot.domain.EntrustOrder;
import demo.springboot.domain.MatchOrder;

/**
 * @author LILUO
 * @date 2020/06/23
 */
public interface IMatchOrderService extends IService<MatchOrder> {

    /**
     * 撮合订单
     *
     * @param buyOrder
     * @param sellOrder
     * @return
     */
    boolean matchOrder(EntrustOrder buyOrder, EntrustOrder sellOrder);
}
