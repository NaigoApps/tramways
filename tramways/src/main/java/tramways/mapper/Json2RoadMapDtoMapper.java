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
import tramways.dto.properties.DecimalPropertyDto;
import tramways.dto.properties.DistributionPropertyDto;
import tramways.dto.properties.IntegerPropertyDto;
import tramways.dto.properties.PropertyDto;
import tramways.dto.properties.StringPropertyDto;

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
		
		RuntimeTypeAdapterFactory<PropertyDto> propertyFactory = RuntimeTypeAdapterFactory
				.of(PropertyDto.class)
				.registerSubtype(IntegerPropertyDto.class, "integer")
				.registerSubtype(DecimalPropertyDto.class, "decimal")
				.registerSubtype(StringPropertyDto.class, "text")
				.registerSubtype(DistributionPropertyDto.class, "distribution");
		
		return new GsonBuilder().setPrettyPrinting()
			.registerTypeAdapterFactory(pointsFactory)
			.registerTypeAdapterFactory(distributionsFactory)
			.registerTypeAdapterFactory(trafficLightsFactory)
			.registerTypeAdapterFactory(propertyFactory)
			.create();
	}
	
}
