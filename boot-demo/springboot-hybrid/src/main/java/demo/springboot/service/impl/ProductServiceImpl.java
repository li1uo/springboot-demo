package demo.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import demo.springboot.common.domain.ResultCode;
import demo.springboot.common.exception.ServiceException;
import demo.springboot.domain.Order;
import demo.springboot.domain.Product;
import demo.springboot.mapper.ProductMapper;
import demo.springboot.service.IOrderService;
import demo.springboot.service.IProductService;
import demo.springboot.tool.util.DateUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;

/**
 * @author LILUO
 * @date 2020/04/09
 */
@AllArgsConstructor
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    private IOrderService orderService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean purchase(Long id) {
        Product product = baseMapper.selectById(id);
        if (Objects.isNull(product) || product.getAmount() == 0){
            throw new ServiceException(ResultCode.AMOUNT_NOT_ENOUGH);
        }

        product.setAmount(product.getAmount() - 1);
        if (!this.updateById(product)){
            throw new ServiceException(ResultCode.UPDATE_DATA_FAIL);
        }

        Order order = new Order();
        String orderNo = "T" + DateUtil.format(new Date(), "yyyyyMMddhhmmss") + RandomStringUtils.randomNumeric(6);
        order.setOrderNo(orderNo);
        order.setProductId(product.getId());

        return orderService.save(order);
    }
}
