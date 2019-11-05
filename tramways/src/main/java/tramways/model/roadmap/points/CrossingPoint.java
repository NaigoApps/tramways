package tramways.model.roadmap.points;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CrossingPoint extends RelevantPoint {

	private Map<String, Set<LaneSegmentLink>> constraints;

	public CrossingPoint() {
		constraints = new HashMap<>();
	}

	public Map<String, Set<LaneSegmentLink>> getConstraints() {
		return constraints;
	}
	
	public void setConstraints(Map<String, Set<LaneSegmentLink>> constraints) {
		this.constraints = constraints;
	}

	public Set<LaneSegmentLink> getConstraints(String laneUuid) {
		return constraints.get(laneUuid);
	}
	
}
