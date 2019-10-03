package tramways.services;

import java.util.Calendar;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import tramways.model.auth.Role;
import tramways.model.auth.User;

public class TokenManager {

	public String token2User(String jwt) {
		try {
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256("tramways")).build();

			DecodedJWT result = verifier.verify(jwt);
			return result.getClaim("userId").asString();
		} catch (JWTDecodeException ex) {
			return null;
		}
	}

	public String requestToken(User user) {
		return createJWT(user);
	}

	private String createJWT(User user) {
		//@formatter:off
		return JWT.create()
				.withExpiresAt(nextDay())
				.withClaim("userId", user.getUuid())
				.withClaim("userName", user.getUsername())
				.withArrayClaim("userRoles", user.listRoles().stream().map(Role::name).toArray(String[]::new))
				.sign(Algorithm.HMAC256("tramways"));
		//@formatter:on
	}

	private Date nextDay() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		return c.getTime();
	}

}
