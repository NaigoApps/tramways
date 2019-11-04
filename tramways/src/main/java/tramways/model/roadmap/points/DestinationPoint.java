package tramways.model.roadmap.points;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tramways.model.AbstractIdentifiable;
import tramways.model.roadmap.lanes.LaneSegment;

public class DestinationPoint extends AbstractIdentifiable implements RelevantPoint {

	private List<LaneSegment> lanes;
	
	public DestinationPoint() {
		lanes = new ArrayList<>();
	}
	
	public void addLane(LaneSegment l) {
		this.lanes.add(l);
	}

	@Override
	public Set<LaneSegment> getSources() {
		return new HashSet<>(lanes);
	}

	@Override
	public Set<LaneSegment> getDestinations() {
		return Collections.emptySet();
	}
}
