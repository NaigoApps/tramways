package tramways.dto.mappers;

import org.mapstruct.Mapper;

import tramways.model.persistable.properties.PropertyWrapper;
import tramways.model.properties.Property;

@Mapper(config = MapperConfiguration.class)
public abstract class PropertyMapper {

	public Property map(PropertyWrapper property) {
		return property.retrieveContent();
	}

	public PropertyWrapper map(Property dto) {
		PropertyWrapper result = new PropertyWrapper();
		result.assignContent(dto);
		return result;
	}

}