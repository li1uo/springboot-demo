package demo.springboot.config.jwt;

import java.util.HashMap;
import java.util.Map;

/**
 * 认证工具类
 */
public class TokenUtil {

	/**
	 * 创建认证token
	 *
	 * @param userInfo 用户信息
	 * @return token
	 */
	public static AuthInfo createAuthInfo(UserInfo userInfo) {

		// 设置jwt参数
		Map<String, String> param = new HashMap<>(16);
		param.put(TokenConstant.TOKEN_TYPE, TokenConstant.ACCESS_TOKEN);
		param.put(TokenConstant.USER_ID, userInfo.getUserId().toString());
		param.put(TokenConstant.USER_NAME, userInfo.getUserName());

		TokenInfo accessToken = SecureUtil.createJWT(param, "audience", "issuser", TokenConstant.ACCESS_TOKEN);
		AuthInfo authInfo = new AuthInfo();
		authInfo.setUserName(userInfo.getUserName());
		authInfo.setUserId(userInfo.getUserId());
		authInfo.setAccessToken(accessToken.getToken());
		authInfo.setExpiresIn(accessToken.getExpire());
		authInfo.setRefreshToken(createRefreshToken(userInfo).getToken());
		authInfo.setTokenType(TokenConstant.BEARER);

		return authInfo;
	}

	/**
	 * 创建refreshToken
	 *
	 * @param userInfo 用户信息
	 * @return refreshToken
	 */
	private static TokenInfo createRefreshToken(UserInfo userInfo) {
		Map<String, String> param = new HashMap<>(16);
		param.put(TokenConstant.TOKEN_TYPE, TokenConstant.REFRESH_TOKEN);
		param.put(TokenConstant.USER_ID, userInfo.getUserId().toString());
		return SecureUtil.createJWT(param, "audience", "issuser", TokenConstant.REFRESH_TOKEN);
	}

}
