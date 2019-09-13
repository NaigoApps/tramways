package tramways.model.points;

import java.util.Set;

import tramways.model.streets.LaneSegment;

public interface RelevantPoint {
	public Set<LaneSegment> getSources();
	public Set<LaneSegment> getDestinations();
}
