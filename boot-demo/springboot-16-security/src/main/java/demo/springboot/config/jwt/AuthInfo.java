package demo.springboot.config.jwt;


import lombok.Data;

/**
 * AuthInfo
 */
@Data
public class AuthInfo {

	/**
	 * 令牌
	 */
	private String accessToken;

	/**
	 * 令牌类型
	 */
	private String tokenType;

	/**
	 * 刷新令牌
	 */
	private String refreshToken;

	/**
	 * 头像
	 */
	private String avatar = "https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png";

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 过期时间
	 */
	private long expiresIn;
}
