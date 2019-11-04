package tramways.model.roadmap.points.trafficlight;

import java.util.HashMap;
import java.util.Map;

import tramways.model.roadmap.lanes.LaneSegment;
import tramways.model.roadmap.points.CrossingPoint;

public class TrafficLightCrossingPoint extends CrossingPoint{
	private Map<LaneSegment, TrafficLight> trafficLights;
	
	public TrafficLightCrossingPoint() {
		trafficLights = new HashMap<>();
	}
	
	public void setTrafficLight(LaneSegment segment, TrafficLight trafficLight) {
		trafficLights.put(segment, trafficLight);
	}
}
