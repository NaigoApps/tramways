package tramways.model.points.trafficlight;

import java.util.HashMap;
import java.util.Map;

import tramways.model.points.CrossingPoint;
import tramways.model.streets.LaneSegment;

public class TrafficLightCrossingPoint extends CrossingPoint{
	private Map<LaneSegment, TrafficLight> trafficLights;
	
	public TrafficLightCrossingPoint() {
		trafficLights = new HashMap<>();
	}
	
	public void setTrafficLight(LaneSegment segment, TrafficLight trafficLight) {
		trafficLights.put(segment, trafficLight);
	}
}
