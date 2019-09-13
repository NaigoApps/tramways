package tramways.dto.points.trafficlight;

import java.util.Map;

import tramways.dto.points.CrossingPointDto;

public class TrafficLightCrossingPointDto extends CrossingPointDto {
	private Map<String, TrafficLightDto> trafficLights;

	public Map<String, TrafficLightDto> getTrafficLights() {
		return trafficLights;
	}

	public void setTrafficLights(Map<String, TrafficLightDto> trafficLights) {
		this.trafficLights = trafficLights;
	}

}
