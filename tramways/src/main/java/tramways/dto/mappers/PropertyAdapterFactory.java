package tramways.dto.mappers;

import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import tramways.core.model.propertiess.ChoiceProperty;
import tramways.core.model.propertiess.DecimalProperty;
import tramways.core.model.propertiess.DistributionProperty;
import tramways.core.model.propertiess.IntegerProperty;
import tramways.core.model.propertiess.Property;
import tramways.core.model.propertiess.PropertyType;
import tramways.core.model.propertiess.StringProperty;

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
