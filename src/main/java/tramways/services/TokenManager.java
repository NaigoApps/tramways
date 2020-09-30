package tramways.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import java.util.Arrays;
import java.util.stream.Collectors;
import tramways.core.model.persistable.users.Role;
import tramways.rs.TramwaysPrincipal;

public class TokenManager {

    private static final String UUID_CLAIM = "uuid";
    private static final String USER_CLAIM = "username";
    private static final String ROLES_CLAIM = "roles";

    private static final String PASSWORD = "tramways";

    public TramwaysPrincipal token2Principal(String jwt) {
        DecodedJWT decodedJWT = decodeJWT(jwt);
        if (decodedJWT != null) {
            return new TramwaysPrincipal(
                decodedJWT.getClaim(UUID_CLAIM).asString(),
                decodedJWT.getClaim(USER_CLAIM).asString(),
                Arrays.stream(decodedJWT.getClaim(ROLES_CLAIM).asArray(String.class))
                    .map(Role::valueOf)
                    .collect(Collectors.toSet())
            );
        }
        return null;
    }

    private DecodedJWT decodeJWT(String jwt) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(PASSWORD)).build();
            return verifier.verify(jwt);
        } catch (JWTDecodeException ex) {
            return null;
        }
    }
}
