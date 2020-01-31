package demo.springboot.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @author LILUO
 * @date 2020/01/31
 */
@Document(indexName = "storage")
@Data
public class Storage implements Serializable {

    private Long id;

    private String name;

    private Long number;
}
