package tramways.core.model.roadmap.points.trafficlight;

import java.util.Map;

import tramways.core.model.roadmap.points.CrossingPoint;

public class TrafficLightCrossingPoint extends CrossingPoint {
	private Map<String, TrafficLight> trafficLights;

	public Map<String, TrafficLight> getTrafficLights() {
		return trafficLights;
	}

	public void setTrafficLights(Map<String, TrafficLight> trafficLights) {
		this.trafficLights = trafficLights;
	}

}
