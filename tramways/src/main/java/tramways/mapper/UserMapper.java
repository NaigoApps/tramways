package tramways.mapper;

import org.mapstruct.Mapper;

import tramways.dto.UserDto;
import tramways.model.auth.User;

@Mapper(config = MapperConfiguration.class)
public interface UserMapper {

	public abstract UserDto map(User u);
	
}
