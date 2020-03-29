package tramways.dto.mappers;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import tramways.core.model.persistable.users.Role;
import tramways.core.model.persistable.users.User;
import tramways.inbound.RestUtils;
import tramways.inbound.model.UserRole;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(config = MapperConfiguration.class)
public abstract class UserMapper {

    @Mapping(target = "actions", ignore = true)
    @Mapping(target = "roles", expression = "java(map(u.listRoles()))")
    public abstract tramways.inbound.model.User map(User u);

    @AfterMapping
    protected void afterMapping(@MappingTarget tramways.inbound.model.User dto, User u) {
        dto.setActions(
                RestUtils.actions("/users", u.getUuid())
                        .action("Enable", "enable")
                        .action("Reset", "reset")
                        .action("Set roles", "roles")
        .getActions());
		dto.getActions().add(RestUtils.action("Enable", "/users/" + u.getUuid() + "/enable"));
    }

    public List<UserRole> map(Set<Role> roles) {
        return roles.stream()
                .map(UserMapper::map)
                .collect(Collectors.toList());
    }

    public abstract List<tramways.inbound.model.User> map(List<User> users);

    protected static UserRole map(Role role) {
        return UserRole.valueOf(role.name());
    }

    public Role map(UserRole userRole) {
        return Role.valueOf(userRole.name());
    }
}
