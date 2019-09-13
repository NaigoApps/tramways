package tramways.dto.points.trafficlight;

import java.util.Set;

public class SensorTrafficLightDto implements TrafficLightDto {

	private Set<String> activators;

	private Integer anticipation;

	public Set<String> getActivators() {
		return activators;
	}

	public void setActivators(Set<String> activators) {
		this.activators = activators;
	}

	public Integer getAnticipation() {
		return anticipation;
	}

	public void setAnticipation(Integer anticipation) {
		this.anticipation = anticipation;
	}

}
