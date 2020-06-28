package demo.springboot.util;

import lombok.SneakyThrows;

import java.util.Base64;

/**
 * @author LILUO
 * @date 2020/06/28
 */
public class TokenUtil {

    public static final String USER_FLAG = "token";

    /**
     * 生成token
     *
     * @param value
     * @return
     */
    public static String generate(String value){
        return Base64.getEncoder().encodeToString(value.getBytes());
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public static String parse(String token){
        return new String(Base64.getDecoder().decode(token));
    }
}
