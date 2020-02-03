package demo.springboot.model;

import demo.springboot.common.domain.elasticsearch.PageParam;
import lombok.Data;

/**
 * @author LILUO
 * @date 2020/02/01
 */
@Data
public class StoragePageDto extends PageParam {

    private String searchContent;
}
