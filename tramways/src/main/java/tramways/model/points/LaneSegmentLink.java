package tramways.model.points;

import org.apache.commons.math3.distribution.RealDistribution;

import tramways.model.AbstractConfigurable;
import tramways.model.distributions.ConstantDistribution;
import tramways.model.distributions.Distribution;
import tramways.model.lanes.LaneSegment;
import tramways.model.properties.Property;

public class LaneSegmentLink extends AbstractConfigurable {

	public static final String CROSSING_TIME = "crossingTime";

	private LaneSegment destination;
	private int weight;

	public LaneSegmentLink(LaneSegment destination, int weight) {
		this.destination = destination;
		this.weight = weight;
	}

	public LaneSegment getDestination() {
		return destination;
	}

	public int getWeight() {
		return weight;
	}

	public void setCrossingTime(Distribution distribution) {
		apply(Property.create(CROSSING_TIME, distribution));
	}

	public void setCrossingTime(Long crossingTime) {
		apply(Property.create(CROSSING_TIME, crossingTime));
	}

	public RealDistribution getCrossingTime() {
		Distribution distribution = getDistributionProperty(CROSSING_TIME);
		if (distribution != null) {
			return distribution.getRealDistribution();
		} else {
			Long value = getIntegerProperty(CROSSING_TIME);
			if (value != null) {
				ConstantDistribution result = new ConstantDistribution();
				result.setValue(value.doubleValue());
				return result.getRealDistribution();
			}
		}
		return null;
	}

}
