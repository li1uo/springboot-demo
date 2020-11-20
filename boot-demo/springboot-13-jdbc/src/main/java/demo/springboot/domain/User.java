package demo.springboot.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author LILUO
 * @date 2020/11/18
 */
@Data
public class User {

    private Long id;

    private String userName;

    private String userPassword;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    private Integer isDeleted;

    private Long version;
}
