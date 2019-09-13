package tramways.model.points;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.math3.distribution.RealDistribution;

import tramways.model.streets.LaneSegment;

public abstract class SourcePoint<L extends LaneSegment> implements RelevantPoint {

	private L targetLine;

	public void setTargetLine(L targetLine) {
		this.targetLine = targetLine;
	}
	
	public L getTargetLine() {
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
