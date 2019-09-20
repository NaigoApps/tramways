package tramways.model.points;

import org.apache.commons.math3.distribution.RealDistribution;

import tramways.model.lanes.LaneSegment;

public class LaneSegmentLink {

	private LaneSegment destination;
	private int weight;
	private RealDistribution crossingTime;

	public LaneSegmentLink(LaneSegment destination, int weight, RealDistribution crossingTime) {
		this.destination = destination;
		this.weight = weight;
		this.crossingTime = crossingTime;
	}

	public LaneSegment getDestination() {
		return destination;
	}

	public int getWeight() {
		return weight;
	}

	public RealDistribution getCrossingTime() {
		return crossingTime;
	}

}
