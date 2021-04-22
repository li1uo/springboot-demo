package demo.springboot.config.jwt;

/**
 * @author LILUO
 * @date 2020/01/07
 */
public interface TokenConstant {

    String SIGN_KEY = "li1uo";
    String AVATAR = "avatar";
    String HEADER = "Authorization";
    String BEARER = "Basic ";
    String ACCESS_TOKEN = "access_token";
    String REFRESH_TOKEN = "refresh_token";
    String TOKEN_TYPE = "token_type";
    String EXPIRES_IN = "expires_in";
    String USER_ID = "user_id";
    String USER_NAME = "user_name";
    String AUTHORITIES = "authorities";
    String DEFAULT_AVATAR = "https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png";
    Integer AUTH_LENGTH = 7;

    Long ACCESS_TOKEN_VALIDITY = 3600l;
    Long REFRESH_TOKEN_VALIDITY = 7200l;
}
