package tramways.model.points;

import java.util.Set;

import tramways.model.Identifiable;
import tramways.model.lanes.LaneSegment;

public interface RelevantPoint extends Identifiable {
	public Set<LaneSegment> getSources();
	public Set<LaneSegment> getDestinations();
}
