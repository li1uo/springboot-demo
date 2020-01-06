package demo.springboot.core.domain;

/**
 * 客户端发送消息实体
 * @author LILUO
 * @date 2018/7/2
 */
public class ClientMessageVO {

    private String name;

    @Override
    public String toString() {
        return "ClientMessageVO{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
