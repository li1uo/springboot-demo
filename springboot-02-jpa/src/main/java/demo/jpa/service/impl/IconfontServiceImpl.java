package demo.jpa.service.impl;

import demo.jpa.repository.IconFontRepository;
import demo.jpa.service.IIconFontService;
import demo.jpa.domain.IconFontDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author LILUO
 * @date 2018/5/9
 */
@Service("iconfontService")
public class IconfontServiceImpl implements IIconFontService {

    @Autowired
    private IconFontRepository iconFontRepository;

    @Override
    public Page<IconFontDO> findByPage(Pageable pageable) {
        return iconFontRepository.findAll(pageable);
    }
}
