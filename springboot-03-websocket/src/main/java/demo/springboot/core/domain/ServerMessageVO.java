package demo.springboot.core.domain;

/**
 * 服务端发送消息实体
 * @author LILUO
 * @date 2018/7/2
 */
public class ServerMessageVO {
    private String responseMessage;

    public ServerMessageVO(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    @Override
    public String toString() {
        return "ServerMessageVO{" +
                "responseMessage='" + responseMessage + '\'' +
                '}';
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
