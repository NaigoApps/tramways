package tramways.dto.mappers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import tramways.dto.ConfigurationDto;
import tramways.model.distributions.ConstantDistribution;
import tramways.model.distributions.Distribution;
import tramways.model.distributions.ExponentialDistribution;
import tramways.model.distributions.UniformDistribution;
import tramways.model.properties.DecimalProperty;
import tramways.model.properties.DistributionProperty;
import tramways.model.properties.IntegerProperty;
import tramways.model.properties.Property;
import tramways.model.properties.PropertyType;
import tramways.model.properties.StringProperty;

public class Json2ConfigurationDtoMapper {
	
	private Gson mapper;
	
	public Json2ConfigurationDtoMapper() {
		mapper = initMapper();
	}
	
	public ConfigurationDto map(String json) {
		return mapper.fromJson(json, ConfigurationDto.class);
	}
	
	private Gson initMapper() {
		RuntimeTypeAdapterFactory<Distribution> distributionsFactory = RuntimeTypeAdapterFactory
				.of(Distribution.class)
				.registerSubtype(ConstantDistribution.class, "constant")
				.registerSubtype(UniformDistribution.class, "uniform")
				.registerSubtype(ExponentialDistribution.class, "exponential");
		
		RuntimeTypeAdapterFactory<Property> propertyFactory = RuntimeTypeAdapterFactory
				.of(Property.class, "type", true)
				.registerSubtype(IntegerProperty.class, PropertyType.INTEGER.name())
				.registerSubtype(DecimalProperty.class, PropertyType.DECIMAL.name())
				.registerSubtype(StringProperty.class, PropertyType.STRING.name())
				.registerSubtype(DistributionProperty.class, PropertyType.DISTRIBUTION.name());
		
		return new GsonBuilder()
				.registerTypeAdapterFactory(distributionsFactory)
				.registerTypeAdapterFactory(propertyFactory)
				.create();
	}
	
}
