package tramways.dto.points;

import tramways.dto.distributions.DistributionDto;

public class LaneSegmentLinkDto {

	private String destination;
	private int weight;
	private DistributionDto crossingTime;

	public String getDestination() {
		return destination;
	}

	public int getWeight() {
		return weight;
	}

	public DistributionDto getCrossingTime() {
		return crossingTime;
	}

}
