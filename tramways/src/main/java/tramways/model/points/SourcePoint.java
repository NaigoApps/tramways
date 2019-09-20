package tramways.model.points;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.math3.distribution.RealDistribution;

import tramways.model.AbstractIdentifiable;
import tramways.model.lanes.LaneSegment;

public abstract class SourcePoint extends AbstractIdentifiable implements RelevantPoint {
	
	private LaneSegment targetLine;

	public void setTargetLine(LaneSegment targetLine) {
		this.targetLine = targetLine;
	}
	
	public LaneSegment getTargetLine() {
		return targetLine;
	}

	@Override
	public Set<LaneSegment> getSources() {
		return Collections.emptySet();
	}

	@Override
	public Set<LaneSegment> getDestinations() {
		return new HashSet<>(Arrays.asList(targetLine));
	}

	public abstract RealDistribution getRate();
}
