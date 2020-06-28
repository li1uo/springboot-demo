package demo.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.security.Principal;

/**
 * @author LILUO
 * @date 2020/02/02
 */
@AllArgsConstructor
@Data
public class UserPrincipal implements Principal {

    private String name;
}
