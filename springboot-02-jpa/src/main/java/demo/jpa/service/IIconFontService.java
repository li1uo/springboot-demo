package demo.jpa.service;

import demo.jpa.domain.IconFontDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author LILUO
 * @date 2018/5/9
 */
public interface IIconFontService {
    /**
     * 分页查询图标信息
     * @param pageable
     * @return
     */
    Page<IconFontDO> findByPage(Pageable pageable);
}
