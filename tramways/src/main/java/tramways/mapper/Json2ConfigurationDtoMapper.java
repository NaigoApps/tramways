package tramways.mapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import tramways.dto.ConfigurationDto;
import tramways.dto.distributions.ConstantDistributionDto;
import tramways.dto.distributions.ExponentialDistributionDto;
import tramways.dto.distributions.DistributionDto;
import tramways.dto.distributions.UniformDistributionDto;
import tramways.dto.properties.DecimalPropertyDto;
import tramways.dto.properties.DistributionPropertyDto;
import tramways.dto.properties.IntegerPropertyDto;
import tramways.dto.properties.PropertyDto;
import tramways.dto.properties.StringPropertyDto;

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
		
		RuntimeTypeAdapterFactory<PropertyDto> propertyFactory = RuntimeTypeAdapterFactory
				.of(PropertyDto.class)
				.registerSubtype(IntegerPropertyDto.class, "integer")
				.registerSubtype(DecimalPropertyDto.class, "decimal")
				.registerSubtype(StringPropertyDto.class, "string")
				.registerSubtype(DistributionPropertyDto.class, "distribution");
		
		return new GsonBuilder().setPrettyPrinting()
			.registerTypeAdapterFactory(distributionsFactory)
			.registerTypeAdapterFactory(propertyFactory)
			.create();
	}
	
}
