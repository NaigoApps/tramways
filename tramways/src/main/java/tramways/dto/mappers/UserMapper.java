package tramways.dto.mappers;

import java.util.HashSet;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import tramways.dto.UserDto;
import tramways.model.persistable.users.User;

@Mapper(config = MapperConfiguration.class)
public interface UserMapper {

	@Mapping(target = "roles", ignore = true)
	public abstract UserDto map(User u);

	@Mapping(target = "toBeDeleted", ignore = true)
	public abstract User map(UserDto u);

	@AfterMapping
	default void afterMapping(@MappingTarget UserDto dto, User u) {
		dto.setRoles(new HashSet<>(u.listRoles()));
	}

}
