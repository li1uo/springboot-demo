package demo.rest.service.impl;

import demo.rest.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author LILUO
 * @date 2019/02/13
 */
@Service("orderService")
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List getOrderList() {
        return restTemplate.getForObject("http://service-member/member/list", List.class);
    }
}
