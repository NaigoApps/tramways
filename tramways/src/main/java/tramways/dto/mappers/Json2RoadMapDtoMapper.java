package tramways.dto.mappers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

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
import tramways.model.roadmap.RoadMap;
import tramways.model.roadmap.points.CrossingPoint;
import tramways.model.roadmap.points.DestinationPoint;
import tramways.model.roadmap.points.RelevantPoint;
import tramways.model.roadmap.points.SourcePoint;
import tramways.model.roadmap.points.trafficlight.SensorTrafficLight;
import tramways.model.roadmap.points.trafficlight.TimedTrafficLight;
import tramways.model.roadmap.points.trafficlight.TrafficLightCrossingPoint;
import tramways.model.roadmap.points.trafficlight.TrafficLight;

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
