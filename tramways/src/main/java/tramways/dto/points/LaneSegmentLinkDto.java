package tramways.dto.points;

import tramways.dto.distributions.RealDistributionDto;

public class LaneSegmentLinkDto {

	private String destination;
	private int weight;
	private RealDistributionDto crossingTime;

	public String getDestination() {
		return destination;
	}

	public int getWeight() {
		return weight;
	}

	public RealDistributionDto getCrossingTime() {
		return crossingTime;
	}

}
