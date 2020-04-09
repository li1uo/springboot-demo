package demo.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import demo.springboot.domain.Product;

/**
 * @author LILUO
 * @date 2020/04/09
 */
public interface IProductService extends IService<Product> {

    boolean purchase(Long id);
}
