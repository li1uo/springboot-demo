package demo.springboot.core.domain;

import java.security.Principal;

/**
 * @author LILUO
 * @date 2018/7/2
 */
public class WebsocketUserVO implements Principal{

    public WebsocketUserVO(String name) {
        this.name = name;
    }

    private String name;

    @Override
    public String getName() {
        return this.name;
    }
}
