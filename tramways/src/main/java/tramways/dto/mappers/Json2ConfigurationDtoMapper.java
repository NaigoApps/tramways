package tramways.dto.mappers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import tramways.dto.ConfigurationDto;
import tramways.model.distributions.Distribution;
import tramways.model.properties.Property;

public class Json2ConfigurationDtoMapper {
	
	private Gson mapper;
	
	public Json2ConfigurationDtoMapper() {
		mapper = initMapper();
	}
	
	public ConfigurationDto map(String json) {
		return mapper.fromJson(json, ConfigurationDto.class);
	}
	
	private Gson initMapper() {
		RuntimeTypeAdapterFactory<Distribution> distributionsFactory = DistributionAdapterFactory.getFactory();
		RuntimeTypeAdapterFactory<Property> propertyFactory = PropertyAdapterFactory.getFactory();
		
		return new GsonBuilder()
				.registerTypeAdapterFactory(distributionsFactory)
				.registerTypeAdapterFactory(propertyFactory)
				.create();
	}
	
}
