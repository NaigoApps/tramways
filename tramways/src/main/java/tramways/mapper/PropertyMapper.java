package tramways.mapper;

import org.mapstruct.Mapper;

import tramways.model.persistable.properties.PropertyWrapper;
import tramways.model.properties.Property;

@Mapper(config = MapperConfiguration.class)
public abstract class PropertyMapper {

	public Property map(Property property) {
		return property.retrieveContent();
	}

	public Property map(Property dto) {
		Property result = new Property();
		result.assignContent(dto);
		return result;
	}

}
