package tramways.rs;

import java.security.Principal;
import javax.ws.rs.core.SecurityContext;
import tramways.core.model.persistable.users.Role;

public class TramwaysSecurityContext implements SecurityContext {

    private final TramwaysPrincipal principal;

    public TramwaysSecurityContext(TramwaysPrincipal user) {
        this.principal = user;
    }

    @Override
    public Principal getUserPrincipal() {
        return principal;
    }

    @Override
    public boolean isUserInRole(String role) {
        return principal.hasRole(Role.valueOf(role));
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }
}
