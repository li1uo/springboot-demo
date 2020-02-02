package demo.springboot.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import demo.springboot.domian.UserDO;
import demo.springboot.domian.UserPageDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LILUO
 * @date 2020/02/02
 */
public interface UserMapper extends BaseMapper<UserDO> {

    /**
     * 获取一页用户信息
     *
     * @param page
     * @param userPageDto
     * @return
     */
    List<UserDO> listUser(IPage<UserDO> page, @Param("userPageDto") UserPageDto userPageDto);
}
