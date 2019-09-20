package tramways.mapper.properties;

import org.mapstruct.Mapper;

import tramways.dto.properties.StringPropertyDto;
import tramways.mapper.MapperConfiguration;
import tramways.model.properties.StringProperty;

@Mapper(config = MapperConfiguration.class)
public interface StringPropertyMapper {	

	public abstract StringPropertyDto map(StringProperty prop);
	public abstract StringProperty map(StringPropertyDto prop);
	
}
