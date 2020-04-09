package demo.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import demo.springboot.domain.Order;
import demo.springboot.mapper.OrderMapper;
import demo.springboot.service.IOrderService;
import org.springframework.stereotype.Service;

/**
 * @author LILUO
 * @date 2020/04/09
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
