package tramways.mapper;

import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import tramways.dto.properties.DecimalPropertyWrapper;
import tramways.dto.properties.DistributionPropertyWrapper;
import tramways.dto.properties.IntegerPropertyWrapper;
import tramways.dto.properties.PropertyWrapper;
import tramways.dto.properties.StringPropertyWrapper;
import tramways.model.properties.PropertyType;

public class PropertyAdapterFactory {
	
	private PropertyAdapterFactory() {
	}
	
	public static RuntimeTypeAdapterFactory<PropertyWrapper> getFactory(){
		return RuntimeTypeAdapterFactory
				.of(PropertyWrapper.class, "type", true)
				.registerSubtype(IntegerPropertyWrapper.class, PropertyType.INTEGER.name())
				.registerSubtype(DecimalPropertyWrapper.class, PropertyType.DECIMAL.name())
				.registerSubtype(StringPropertyWrapper.class, PropertyType.STRING.name())
				.registerSubtype(DistributionPropertyWrapper.class, PropertyType.DISTRIBUTION.name());
	}
	
}
