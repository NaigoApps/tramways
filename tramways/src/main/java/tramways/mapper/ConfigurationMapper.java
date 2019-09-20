package tramways.mapper;

import org.mapstruct.Mapper;

import tramways.dto.ConfigurationDto;
import tramways.mapper.properties.PropertyMapper;
import tramways.model.Configuration;

@Mapper(config = MapperConfiguration.class, uses = { PropertyMapper.class })
public abstract class ConfigurationMapper {

	public abstract Configuration map(ConfigurationDto dto);
	
	public abstract ConfigurationDto map(Configuration dto);

}
