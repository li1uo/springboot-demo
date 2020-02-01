package demo.springboot.common.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author LILUO
 * @date 2020/02/01
 */
@Data
public class PageParam implements Serializable {

    private int pageIndex = 0;

    private int pageSize = 10;
}
