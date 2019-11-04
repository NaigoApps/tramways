package tramways.mapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import tramways.dto.RoadMap;
import tramways.dto.points.CrossingPointDto;
import tramways.dto.points.DestinationPointDto;
import tramways.dto.points.RelevantPointDto;
import tramways.dto.points.SourcePointDto;
import tramways.dto.points.trafficlight.SensorTrafficLightDto;
import tramways.dto.points.trafficlight.TimedTrafficLightDto;
import tramways.dto.points.trafficlight.TrafficLightCrossingPointDto;
import tramways.dto.points.trafficlight.TrafficLightDto;
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

public class Json2RoadMapDtoMapper {
	
	private Gson mapper;
	
	public Json2RoadMapDtoMapper() {
		mapper = initMapper();
	}
	
	public RoadMap map(String json) {
		return mapper.fromJson(json, RoadMap.class);
	}
	
	private Gson initMapper() {
		RuntimeTypeAdapterFactory<RelevantPointDto> pointsFactory = RuntimeTypeAdapterFactory
				.of(RelevantPointDto.class)
				.registerSubtype(SourcePointDto.class, "source")
				.registerSubtype(CrossingPointDto.class, "crossing")
				.registerSubtype(TrafficLightCrossingPointDto.class, "trafficLight")				
				.registerSubtype(DestinationPointDto.class, "destination");

		RuntimeTypeAdapterFactory<Distribution> distributionsFactory = RuntimeTypeAdapterFactory
				.of(Distribution.class)
				.registerSubtype(ConstantDistribution.class, "constant")
				.registerSubtype(UniformDistribution.class, "uniform")
				.registerSubtype(ExponentialDistribution.class, "exponential");
		
		RuntimeTypeAdapterFactory<TrafficLightDto> trafficLightsFactory = RuntimeTypeAdapterFactory
				.of(TrafficLightDto.class)
				.registerSubtype(TimedTrafficLightDto.class, "timed")
				.registerSubtype(SensorTrafficLightDto.class, "sensor");
		
		RuntimeTypeAdapterFactory<Property> propertyFactory = RuntimeTypeAdapterFactory
				.of(Property.class)
				.registerSubtype(IntegerProperty.class, PropertyType.INTEGER.name())
				.registerSubtype(DecimalProperty.class, PropertyType.DECIMAL.name())
				.registerSubtype(StringProperty.class, PropertyType.STRING.name())
				.registerSubtype(DistributionProperty.class, PropertyType.DISTRIBUTION.name());
		
		return new GsonBuilder().setPrettyPrinting()
			.registerTypeAdapterFactory(pointsFactory)
			.registerTypeAdapterFactory(distributionsFactory)
			.registerTypeAdapterFactory(trafficLightsFactory)
			.registerTypeAdapterFactory(propertyFactory)
			.create();
	}
	
}
