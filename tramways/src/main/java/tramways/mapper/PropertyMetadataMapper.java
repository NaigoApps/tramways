package tramways.mapper;

import org.mapstruct.Mapper;

import tramways.model.persistable.properties.PropertyMetadata;
import tramways.model.properties.PropertyMetadataDto;

@Mapper(config = MapperConfiguration.class)
public interface PropertyMetadataMapper {
	
	public PropertyMetadataDto map(PropertyMetadata meta);
	public PropertyMetadata map(PropertyMetadataDto meta);
	
}
