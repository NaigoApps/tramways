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
				.registerSubtype(IntegerProperty.class, PropertyType.INTEGER.getName())
				.registerSubtype(DecimalProperty.class, PropertyType.DECIMAL.getName())
				.registerSubtype(StringProperty.class, PropertyType.STRING.getName())
				.registerSubtype(ChoiceProperty.class, PropertyType.CHOICE.getName())
				.registerSubtype(DistributionProperty.class, PropertyType.DISTRIBUTION.getName());
	}
	
}
