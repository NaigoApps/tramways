package tramways.mapper;

import org.mapstruct.Mapper;

import tramways.dto.properties.PropertyWrapper;
import tramways.model.properties.Property;

@Mapper(config = MapperConfiguration.class)
public abstract class PropertyMapper {

	public PropertyWrapper map(Property property) {
		return property.retrieveContent();
	}

	public Property map(PropertyWrapper dto) {
		Property result = new Property();
		result.assignContent(dto);
		return result;
	}

}
