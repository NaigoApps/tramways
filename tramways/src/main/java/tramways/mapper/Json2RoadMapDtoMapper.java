package tramways.mapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import tramways.dto.RoadMapDto;
import tramways.dto.distributions.ConstantDistributionDto;
import tramways.dto.distributions.DistributionDto;
import tramways.dto.distributions.ExponentialDistributionDto;
import tramways.dto.distributions.UniformDistributionDto;
import tramways.dto.points.CrossingPointDto;
import tramways.dto.points.DestinationPointDto;
import tramways.dto.points.RelevantPointDto;
import tramways.dto.points.SourcePointDto;
import tramways.dto.points.trafficlight.SensorTrafficLightDto;
import tramways.dto.points.trafficlight.TimedTrafficLightDto;
import tramways.dto.points.trafficlight.TrafficLightCrossingPointDto;
import tramways.dto.points.trafficlight.TrafficLightDto;
import tramways.dto.properties.DecimalPropertyWrapper;
import tramways.dto.properties.DistributionPropertyWrapper;
import tramways.dto.properties.IntegerPropertyWrapper;
import tramways.dto.properties.PropertyWrapper;
import tramways.dto.properties.StringPropertyWrapper;
import tramways.model.properties.PropertyType;

public class Json2RoadMapDtoMapper {
	
	private Gson mapper;
	
	public Json2RoadMapDtoMapper() {
		mapper = initMapper();
	}
	
	public RoadMapDto map(String json) {
		return mapper.fromJson(json, RoadMapDto.class);
	}
	
	private Gson initMapper() {
		RuntimeTypeAdapterFactory<RelevantPointDto> pointsFactory = RuntimeTypeAdapterFactory
				.of(RelevantPointDto.class)
				.registerSubtype(SourcePointDto.class, "source")
				.registerSubtype(CrossingPointDto.class, "crossing")
				.registerSubtype(TrafficLightCrossingPointDto.class, "trafficLight")				
				.registerSubtype(DestinationPointDto.class, "destination");

		RuntimeTypeAdapterFactory<DistributionDto> distributionsFactory = RuntimeTypeAdapterFactory
				.of(DistributionDto.class)
				.registerSubtype(ConstantDistributionDto.class, "constant")
				.registerSubtype(UniformDistributionDto.class, "uniform")
				.registerSubtype(ExponentialDistributionDto.class, "exponential");
		
		RuntimeTypeAdapterFactory<TrafficLightDto> trafficLightsFactory = RuntimeTypeAdapterFactory
				.of(TrafficLightDto.class)
				.registerSubtype(TimedTrafficLightDto.class, "timed")
				.registerSubtype(SensorTrafficLightDto.class, "sensor");
		
		RuntimeTypeAdapterFactory<PropertyWrapper> propertyFactory = RuntimeTypeAdapterFactory
				.of(PropertyWrapper.class)
				.registerSubtype(IntegerPropertyWrapper.class, PropertyType.INTEGER.name())
				.registerSubtype(DecimalPropertyWrapper.class, PropertyType.DECIMAL.name())
				.registerSubtype(StringPropertyWrapper.class, PropertyType.STRING.name())
				.registerSubtype(DistributionPropertyWrapper.class, PropertyType.DISTRIBUTION.name());
		
		return new GsonBuilder().setPrettyPrinting()
			.registerTypeAdapterFactory(pointsFactory)
			.registerTypeAdapterFactory(distributionsFactory)
			.registerTypeAdapterFactory(trafficLightsFactory)
			.registerTypeAdapterFactory(propertyFactory)
			.create();
	}
	
}
