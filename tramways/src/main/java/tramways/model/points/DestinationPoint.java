package tramways.model.points;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tramways.model.AbstractIdentifiable;
import tramways.model.lanes.LaneSegment;

public class DestinationPoint extends AbstractIdentifiable implements RelevantPoint {

	private List<LaneSegment> lines;
	
	public DestinationPoint() {
		lines = new ArrayList<>();
	}
	
	public void addLine(LaneSegment l) {
		this.lines.add(l);
	}

	@Override
	public Set<LaneSegment> getSources() {
		return new HashSet<>(lines);
	}

	@Override
	public Set<LaneSegment> getDestinations() {
		return Collections.emptySet();
	}
}
