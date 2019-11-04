package tramways.model.roadmap.points;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.math3.distribution.RealDistribution;

import tramways.model.AbstractConfigurable;
import tramways.model.roadmap.lanes.LaneSegment;

public abstract class SourcePoint extends AbstractConfigurable implements RelevantPoint {
	
	private LaneSegment targetLane;

	public void setTargetLane(LaneSegment targetLane) {
		this.targetLane = targetLane;
	}
	
	public LaneSegment getTargetLane() {
		return targetLane;
	}

	@Override
	public Set<LaneSegment> getSources() {
		return Collections.emptySet();
	}

	@Override
	public Set<LaneSegment> getDestinations() {
		return new HashSet<>(Arrays.asList(targetLane));
	}

	public abstract RealDistribution getRate();
}
