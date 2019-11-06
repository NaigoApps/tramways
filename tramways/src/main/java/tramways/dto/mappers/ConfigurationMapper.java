package tramways.dto.mappers;

import org.mapstruct.Mapper;

import tramways.dto.ConfigurationDto;
import tramways.model.persistable.configurations.Configuration;

@Mapper(config = MapperConfiguration.class, uses = { PropertyMapper.class })
public interface ConfigurationMapper {

	public abstract Configuration map(ConfigurationDto dto);
	public abstract ConfigurationDto map(Configuration dto);

}