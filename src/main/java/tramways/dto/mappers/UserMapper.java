package tramways.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tramways.core.model.persistable.users.Role;
import tramways.core.model.persistable.users.User;
import tramways.inbound.model.UserRole;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(config = MapperConfiguration.class)
public abstract class UserMapper {

    @Mapping(target = "resourceType", constant = "User")
    @Mapping(target = "roles", expression = "java(toApi(u.listRoles()))")
    public abstract tramways.inbound.model.User map(User u);

    public List<UserRole> toApi(Collection<Role> roles) {
        return roles.stream()
                .map(UserMapper::toApi)
                .collect(Collectors.toList());
    }

    public List<Role> toModel(Collection<UserRole> roles) {
        return roles.stream()
                .map(UserMapper::toModel)
                .collect(Collectors.toList());
    }

    public static Role toModel(UserRole role) {
        return Role.valueOf(role.name());
    }

    public static UserRole toApi(Role role) {
        return UserRole.valueOf(role.name());
    }

    public abstract List<tramways.inbound.model.User> map(List<User> users);
}
