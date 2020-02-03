package demo.springboot.domain;

import lombok.Data;

import java.security.Principal;

/**
 * @author LILUO
 * @date 2020/02/02
 */
@Data
public class WebsocketPrincipal implements Principal {

    public WebsocketPrincipal(String name){
        this.name = name;
    }

    private String name;
}
