package demo.springboot.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * jwt 用户信息
 *
 * @author LILUO
 * @date 2021/04/22
 */
@Setter
@Getter
public class JwtUserDetail extends User {

    private Long userId;

    public JwtUserDetail(String username, String password, Collection<? extends GrantedAuthority> authorities, Long userId) {
        super(username, password, true, true, true, true, authorities);
        this.userId = userId;
    }
}
