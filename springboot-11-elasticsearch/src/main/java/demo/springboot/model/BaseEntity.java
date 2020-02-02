package demo.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Version;
import org.springframework.data.elasticsearch.annotations.Score;

import java.io.Serializable;

/**
 * @author LILUO
 * @date 2020/02/02
 */
@Data
public class BaseEntity implements Serializable {

    @JsonIgnore
    @Score
    private Float score;

    /**
     * 版本号
     */
    @JsonIgnore
    @Version
    private Long version;
}
