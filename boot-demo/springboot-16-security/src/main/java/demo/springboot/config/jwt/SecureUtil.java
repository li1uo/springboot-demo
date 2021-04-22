package demo.springboot.config.jwt;

import demo.springboot.domain.User;
import demo.springboot.tool.util.WebUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Secure工具类
 */
public class SecureUtil {

	private static final String BLADE_USER_REQUEST_ATTR = "_BLADE_USER_REQUEST_ATTR_";

	private final static String HEADER = TokenConstant.HEADER;
	private final static String BEARER = TokenConstant.BEARER;
	private final static String USER_ID = TokenConstant.USER_ID;
	private final static String USER_NAME = TokenConstant.USER_NAME;
	private final static Integer AUTH_LENGTH = TokenConstant.AUTH_LENGTH;
	private static String BASE64_SECURITY = Base64.getEncoder().encodeToString(TokenConstant.SIGN_KEY.getBytes(StandardCharsets.UTF_8));


	/**
	 * 获取用户信息
	 *
	 * @return UserInfo
	 */
	public static UserInfo getUser() {
		HttpServletRequest request = WebUtil.getRequest();
		if (request == null) {
			return null;
		}
		// 优先从 request 中获取
		Object user = request.getAttribute(BLADE_USER_REQUEST_ATTR);
		if (user == null) {
			user = getUser(request);
			if (user != null) {
				// 设置到 request 中
				request.setAttribute(BLADE_USER_REQUEST_ATTR, user);
			}
		}
		return (UserInfo) user;
	}

	/**
	 * 获取用户信息
	 *
	 * @param request request
	 * @return BladeUser
	 */
	public static UserInfo getUser(HttpServletRequest request) {
		Claims claims = getClaims(request);
		if (claims == null) {
			return null;
		}
		Long userId = Long.valueOf(String.valueOf(claims.get(SecureUtil.USER_ID)));
		String userName = String.valueOf(claims.get(SecureUtil.USER_NAME));
		UserInfo user = new UserInfo();
		user.setUserId(userId);
		user.setUserName(userName);
		return user;
	}

	/**
	 * 获取Claims
	 *
	 * @param request request
	 * @return Claims
	 */
	public static Claims getClaims(HttpServletRequest request) {
		String auth = request.getHeader(SecureUtil.HEADER);
		if ((auth != null) && (auth.length() > AUTH_LENGTH)) {
			String headStr = auth.substring(0, 6);
			if (headStr.compareTo(SecureUtil.BEARER) == 0) {
				auth = auth.substring(6);
				return SecureUtil.parseJWT(auth);
			}
		}
		return null;
	}

	/**
	 * 获取请求头
	 *
	 * @param request request
	 * @return header
	 */
	public static String getHeader(HttpServletRequest request) {
		return request.getHeader(HEADER);
	}

	/**
	 * 解析jsonWebToken
	 *
	 * @param jsonWebToken jsonWebToken
	 * @return Claims
	 */
	public static Claims parseJWT(String jsonWebToken) {
		try {
			return Jwts.parser()
				.setSigningKey(Base64.getDecoder().decode(BASE64_SECURITY))
				.parseClaimsJws(jsonWebToken).getBody();
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 创建令牌
	 *
	 * @param user      user
	 * @param audience  audience
	 * @param issuer    issuer
	 * @param tokenType tokenType
	 * @return jwt
	 */
	public static TokenInfo createJWT(Map<String, String> user, String audience, String issuer, String tokenType) {

		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		// 生成签名密钥
		byte[] apiKeySecretBytes = Base64.getDecoder().decode(BASE64_SECURITY);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		// 添加构成JWT的类
		JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JsonWebToken")
			.setIssuer(issuer)
			.setAudience(audience)
			.signWith(signatureAlgorithm, signingKey);

		// 设置JWT参数
		user.forEach(builder::claim);

		// 添加Token过期时间
		long expireMillis;
		if (tokenType.equals(TokenConstant.ACCESS_TOKEN)) {
			expireMillis = TokenConstant.ACCESS_TOKEN_VALIDITY * 1000;
		} else if (tokenType.equals(TokenConstant.REFRESH_TOKEN)) {
			expireMillis = TokenConstant.ACCESS_TOKEN_VALIDITY * 1000;
		} else {
			expireMillis = getExpire();
		}
		long expMillis = nowMillis + expireMillis;
		Date exp = new Date(expMillis);
		builder.setExpiration(exp).setNotBefore(now);

		// 组装Token信息
		TokenInfo tokenInfo = new TokenInfo();
		tokenInfo.setToken(builder.compact());
		tokenInfo.setExpire((int) expireMillis / 1000);

		return tokenInfo;
	}

	/**
	 * 获取过期时间(次日凌晨3点)
	 *
	 * @return expire
	 */
	public static long getExpire() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 1);
		cal.set(Calendar.HOUR_OF_DAY, 3);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis() - System.currentTimeMillis();
	}
}
