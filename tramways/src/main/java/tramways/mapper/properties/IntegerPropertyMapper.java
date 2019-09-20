package tramways.mapper.properties;

import org.mapstruct.Mapper;

import tramways.dto.properties.IntegerPropertyDto;
import tramways.mapper.MapperConfiguration;
import tramways.model.properties.IntegerProperty;

@Mapper(config = MapperConfiguration.class)
public interface IntegerPropertyMapper {

	public abstract IntegerPropertyDto map(IntegerProperty prop);
	public abstract IntegerProperty map(IntegerPropertyDto prop);
	
}
