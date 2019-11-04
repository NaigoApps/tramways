package tramways.model.roadmap.points;

import java.util.Set;

import tramways.model.Identifiable;
import tramways.model.roadmap.lanes.LaneSegment;

public interface RelevantPoint extends Identifiable {
	public Set<LaneSegment> getSources();
	public Set<LaneSegment> getDestinations();
}
