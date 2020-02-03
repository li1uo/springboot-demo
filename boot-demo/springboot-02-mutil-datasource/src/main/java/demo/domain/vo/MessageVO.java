package demo.domain.vo;

import demo.domain.DictDO;
import demo.domain.UserDO;
import lombok.Data;

import java.util.List;

/**
 * @author LILUO
 * @date 2018/5/10
 */
@Data
public class MessageVO {

    private List<DictDO> dictDOList;

    private UserDO userDO;
}
