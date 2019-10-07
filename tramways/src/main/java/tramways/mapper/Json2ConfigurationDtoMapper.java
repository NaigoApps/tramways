package tramways.mapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import tramways.dto.ConfigurationDto;
import tramways.dto.distributions.ConstantDistributionDto;
import tramways.dto.distributions.ExponentialDistributionDto;
import tramways.dto.distributions.DistributionDto;
import tramways.dto.distributions.UniformDistributionDto;
import tramways.dto.properties.DecimalPropertyWrapper;
import tramways.dto.properties.DistributionPropertyWrapper;
import tramways.dto.properties.IntegerPropertyWrapper;
import tramways.dto.properties.PropertyWrapper;
import tramways.dto.properties.StringPropertyWrapper;
import tramways.model.properties.PropertyType;

public class Json2ConfigurationDtoMapper {
	
	private Gson mapper;
	
	public Json2ConfigurationDtoMapper() {
		mapper = initMapper();
	}
	
	public ConfigurationDto map(String json) {
		return mapper.fromJson(json, ConfigurationDto.class);
	}
	
	private Gson initMapper() {
		RuntimeTypeAdapterFactory<DistributionDto> distributionsFactory = RuntimeTypeAdapterFactory
				.of(DistributionDto.class)
				.registerSubtype(ConstantDistributionDto.class, "constant")
				.registerSubtype(UniformDistributionDto.class, "uniform")
				.registerSubtype(ExponentialDistributionDto.class, "exponential");
		
		RuntimeTypeAdapterFactory<PropertyWrapper> propertyFactory = RuntimeTypeAdapterFactory
				.of(PropertyWrapper.class, "type", true)
				.registerSubtype(IntegerPropertyWrapper.class, PropertyType.INTEGER.name())
				.registerSubtype(DecimalPropertyWrapper.class, PropertyType.DECIMAL.name())
				.registerSubtype(StringPropertyWrapper.class, PropertyType.STRING.name())
				.registerSubtype(DistributionPropertyWrapper.class, PropertyType.DISTRIBUTION.name());
		
		return new GsonBuilder()
				.registerTypeAdapterFactory(distributionsFactory)
				.registerTypeAdapterFactory(propertyFactory)
				.create();
	}
	
}
