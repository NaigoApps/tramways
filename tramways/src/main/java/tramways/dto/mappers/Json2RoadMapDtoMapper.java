package tramways.dto.mappers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import tramways.core.model.distributionss.ConstantDistribution;
import tramways.core.model.distributionss.Distribution;
import tramways.core.model.distributionss.ExponentialDistribution;
import tramways.core.model.distributionss.UniformDistribution;
import tramways.core.model.propertiess.Property;
import tramways.core.model.roadmap.RoadMap;
import tramways.core.model.roadmap.points.CrossingPoint;
import tramways.core.model.roadmap.points.DestinationPoint;
import tramways.core.model.roadmap.points.RelevantPoint;
import tramways.core.model.roadmap.points.SourcePoint;
import tramways.core.model.roadmap.points.trafficlight.SensorTrafficLight;
import tramways.core.model.roadmap.points.trafficlight.TimedTrafficLight;
import tramways.core.model.roadmap.points.trafficlight.TrafficLight;
import tramways.core.model.roadmap.points.trafficlight.TrafficLightCrossingPoint;

public class Json2RoadMapDtoMapper {
	
	private Gson mapper;
	
	public Json2RoadMapDtoMapper() {
		mapper = initMapper();
	}
	
	public RoadMap map(String json) {
		RoadMap result = mapper.fromJson(json, RoadMap.class);
		result.initialize();
		return result;
	}
	
	private Gson initMapper() {
		RuntimeTypeAdapterFactory<RelevantPoint> pointsFactory = RuntimeTypeAdapterFactory
				.of(RelevantPoint.class)
				.registerSubtype(SourcePoint.class, "source")
				.registerSubtype(CrossingPoint.class, "crossing")
				.registerSubtype(TrafficLightCrossingPoint.class, "trafficLight")				
				.registerSubtype(DestinationPoint.class, "destination");

		RuntimeTypeAdapterFactory<Distribution> distributionsFactory = RuntimeTypeAdapterFactory
				.of(Distribution.class)
				.registerSubtype(ConstantDistribution.class, "constant")
				.registerSubtype(UniformDistribution.class, "uniform")
				.registerSubtype(ExponentialDistribution.class, "exponential");
		
		RuntimeTypeAdapterFactory<TrafficLight> trafficLightsFactory = RuntimeTypeAdapterFactory
				.of(TrafficLight.class)
				.registerSubtype(TimedTrafficLight.class, "timed")
				.registerSubtype(SensorTrafficLight.class, "sensor");
		
		RuntimeTypeAdapterFactory<Property> propertyFactory = PropertyAdapterFactory.getFactory();
		
		return new GsonBuilder().setPrettyPrinting()
			.registerTypeAdapterFactory(pointsFactory)
			.registerTypeAdapterFactory(distributionsFactory)
			.registerTypeAdapterFactory(trafficLightsFactory)
			.registerTypeAdapterFactory(propertyFactory)
			.create();
	}
	
}
