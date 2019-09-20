package tramways.mapper.properties;

import org.mapstruct.Mapper;

import tramways.dto.properties.DecimalPropertyDto;
import tramways.mapper.MapperConfiguration;
import tramways.model.properties.DecimalProperty;

@Mapper(config = MapperConfiguration.class)
public interface DecimalPropertyMapper {

	public abstract DecimalPropertyDto map(DecimalProperty prop);
	
	public abstract DecimalProperty map(DecimalPropertyDto prop);
	
}
