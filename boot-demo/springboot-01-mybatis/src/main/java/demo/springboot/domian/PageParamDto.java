package demo.springboot.domian;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author LILUO
 * @date 2020/02/02
 */
@ToString
@Getter
public class PageParamDto implements Serializable {

    /**
     * 页码
     */
    private int pageIndex;

    /**
     * 行数
     */
    private int pageSize;

    /**
     * mybatis 分页参数
     */
    private Page page;

    public PageParamDto (){
        this.page = new Page();
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
        this.page.setCurrent(pageIndex);
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        this.page.setSize(pageSize);
    }
}
