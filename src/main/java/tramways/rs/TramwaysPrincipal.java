package tramways.rs;

import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.security.auth.Subject;
import tramways.core.model.persistable.users.Role;

public class TramwaysPrincipal implements Principal {

    private final String userUuid;
    private final String userName;
    private final Set<Role> userRoles;

    public TramwaysPrincipal(String userUuid, String userName,
        Set<Role> userRoles) {
        this.userUuid = userUuid;
        this.userName = userName;
        this.userRoles = userRoles;
    }

    public String getUserUuid() {
        return userUuid;
    }

    @Override
    public String getName() {
        return userName;
    }

    public boolean hasRole(Role role) {
        return userRoles.contains(role);
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }

    public Collection<Role> listRoles() {
        return new HashSet<>(userRoles);
    }
}
