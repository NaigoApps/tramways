package tramways.dto.mappers;

import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import tramways.model.properties.ChoiceProperty;
import tramways.model.properties.DecimalProperty;
import tramways.model.properties.DistributionProperty;
import tramways.model.properties.IntegerProperty;
import tramways.model.properties.Property;
import tramways.model.properties.PropertyType;
import tramways.model.properties.StringProperty;

public class PropertyAdapterFactory {
	
	private PropertyAdapterFactory() {
	}
	
	public static RuntimeTypeAdapterFactory<Property> getFactory(){
		return RuntimeTypeAdapterFactory
				.of(Property.class, "type", true)
				.registerSubtype(IntegerProperty.class, PropertyType.INTEGER.name())
				.registerSubtype(DecimalProperty.class, PropertyType.DECIMAL.name())
				.registerSubtype(StringProperty.class, PropertyType.STRING.name())
				.registerSubtype(ChoiceProperty.class, PropertyType.CHOICE.name())
				.registerSubtype(DistributionProperty.class, PropertyType.DISTRIBUTION.name());
	}
	
}
