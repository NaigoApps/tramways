package tramways.rs;

import tramways.core.model.persistable.users.Role;
import tramways.core.model.persistable.users.User;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.Set;

public class TramwaysSecurityContext implements SecurityContext {

    private Principal principal;
    private final String userName;
    private final Set<Role> userRoles;

    public TramwaysSecurityContext(User user) {
        this.userName = user.getUsername();
        this.userRoles = user.listRoles();
        principal = () -> userName;
    }

    @Override
    public Principal getUserPrincipal() {
        return principal;
    }

    @Override
    public boolean isUserInRole(String role) {
        return userRoles.contains(Role.valueOf(role));
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
