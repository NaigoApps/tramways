package tramways.dto.points;

import tramways.model.distributions.Distribution;

public class LaneSegmentLinkDto {

	private String destination;
	private int weight;
	private Distribution crossingTime;

	public String getDestination() {
		return destination;
	}

	public int getWeight() {
		return weight;
	}

	public Distribution getCrossingTime() {
		return crossingTime;
	}

}
