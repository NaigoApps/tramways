package tramways.mapper;

import org.mapstruct.Mapper;

import tramways.dto.properties.PropertyMetadataDto;
import tramways.model.properties.PropertyMetadata;

@Mapper(config = MapperConfiguration.class)
public interface PropertyMetadataMapper {
	
	public PropertyMetadataDto map(PropertyMetadata meta);
	public PropertyMetadata map(PropertyMetadataDto meta);
	
}
