package demo.springboot.config.jwt;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author LILUO
 * @date 2021/04/22
 */
@Data
public class UserInfo {

    private Long userId;

    private String userName;

    private Collection<GrantedAuthority> authorities;
}
