package demo.springboot.domain.dto;

import demo.springboot.domain.DictDO;

/**
 * @author LILUO
 * @date 2018/5/12
 */
public class DictDto extends DictDO{

    /**
     * 页码
     */
    private Integer pageNumber;

    /**
     * 行数
     */
    private Integer pageSize;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "DictDto{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                '}';
    }
}
