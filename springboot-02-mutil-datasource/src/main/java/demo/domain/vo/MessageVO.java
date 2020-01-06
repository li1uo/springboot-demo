package demo.domain.vo;

import demo.domain.DictDO;
import demo.domain.UserDO;

import java.util.List;

/**
 * @author LILUO
 * @date 2018/5/10
 */
public class MessageVO {

    private List<DictDO> dictDOList;

    private UserDO userDO;

    public List<DictDO> getDictDOList() {
        return dictDOList;
    }

    public void setDictDOList(List<DictDO> dictDOList) {
        this.dictDOList = dictDOList;
    }

    public UserDO getUserDO() {
        return userDO;
    }

    public void setUserDO(UserDO userDO) {
        this.userDO = userDO;
    }

    @Override
    public String toString() {
        return "MessageVO{" +
                "dictDOList=" + dictDOList +
                ", userDO=" + userDO +
                '}';
    }
}
