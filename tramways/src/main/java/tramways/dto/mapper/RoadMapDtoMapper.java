package tramways.dto.mapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import tramways.dto.RoadMapDto;
import tramways.dto.distributions.ConstantDistributionDto;
import tramways.dto.distributions.ExponentialDistributionDto;
import tramways.dto.distributions.RealDistributionDto;
import tramways.dto.distributions.UniformDistributionDto;
import tramways.dto.lanes.CarLaneSegmentDto;
import tramways.dto.lanes.LaneSegmentDto;
import tramways.dto.lanes.TramLaneSegmentDto;
import tramways.dto.points.CarSourcePointDto;
import tramways.dto.points.CrossingPointDto;
import tramways.dto.points.DestinationPointDto;
import tramways.dto.points.RelevantPointDto;
import tramways.dto.points.TramSourcePointDto;
import tramways.dto.points.trafficlight.SensorTrafficLightDto;
import tramways.dto.points.trafficlight.TimedTrafficLightDto;
import tramways.dto.points.trafficlight.TrafficLightCrossingPointDto;
import tramways.dto.points.trafficlight.TrafficLightDto;

public class RoadMapDtoMapper {
	
	private Gson mapper;
	
	public RoadMapDtoMapper() {
		mapper = initMapper();
	}
	
	public RoadMapDto map(String json) {
		return mapper.fromJson(json, RoadMapDto.class);
	}
	
	private Gson initMapper() {
		RuntimeTypeAdapterFactory<RelevantPointDto> pointsFactory = RuntimeTypeAdapterFactory
				.of(RelevantPointDto.class)
				.registerSubtype(CrossingPointDto.class, "crossing")
				.registerSubtype(TrafficLightCrossingPointDto.class, "trafficLight")				
				.registerSubtype(DestinationPointDto.class, "destination")
				.registerSubtype(CarSourcePointDto.class, "carSource")
				.registerSubtype(TramSourcePointDto.class, "tramSource");

		RuntimeTypeAdapterFactory<LaneSegmentDto> lanesFactory = RuntimeTypeAdapterFactory
				.of(LaneSegmentDto.class)
				.registerSubtype(CarLaneSegmentDto.class, "car")
				.registerSubtype(TramLaneSegmentDto.class, "tram");
		
		RuntimeTypeAdapterFactory<RealDistributionDto> distributionsFactory = RuntimeTypeAdapterFactory
				.of(RealDistributionDto.class)
				.registerSubtype(ConstantDistributionDto.class, "constant")
				.registerSubtype(UniformDistributionDto.class, "uniform")
				.registerSubtype(ExponentialDistributionDto.class, "exponential");
		
		RuntimeTypeAdapterFactory<TrafficLightDto> trafficLightsFactory = RuntimeTypeAdapterFactory
				.of(TrafficLightDto.class)
				.registerSubtype(TimedTrafficLightDto.class, "timed")
				.registerSubtype(SensorTrafficLightDto.class, "sensor");
		
		return new GsonBuilder().setPrettyPrinting()
			.registerTypeAdapterFactory(pointsFactory)
			.registerTypeAdapterFactory(lanesFactory)
			.registerTypeAdapterFactory(distributionsFactory)
			.registerTypeAdapterFactory(trafficLightsFactory)
			.create();
	}
	
}
