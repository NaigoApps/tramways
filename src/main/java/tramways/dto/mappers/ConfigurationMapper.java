package tramways.dto.mappers;

import org.mapstruct.Mapper;

import tramways.core.model.persistable.configurations.Configuration;
import tramways.dto.ConfigurationDto;

@Mapper(config = MapperConfiguration.class, uses = { PropertyMapper.class })
public interface ConfigurationMapper {

	public abstract Configuration map(ConfigurationDto dto);
	public abstract ConfigurationDto map(Configuration dto);

}
