package tramways.core.model.roadmap.points;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import tramways.core.model.roadmap.lanes.LaneSegment;

public class CrossingPoint extends RelevantPoint {

	private Map<LaneSegment, Set<LaneSegmentLink>> constraints;

	public CrossingPoint() {
		constraints = new HashMap<>();
	}

	public Map<LaneSegment, Set<LaneSegmentLink>> getConstraints() {
		return constraints;
	}
	
	public void setConstraints(Map<LaneSegment, Set<LaneSegmentLink>> constraints) {
		this.constraints = constraints;
	}

	public Set<LaneSegmentLink> getConstraints(LaneSegment segment) {
		return constraints.get(laneUuid);
	}
	
}
