package tramways.model.points;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import tramways.model.AbstractIdentifiable;
import tramways.model.distributions.Distribution;
import tramways.model.lanes.LaneSegment;

public class CrossingPoint extends AbstractIdentifiable implements RelevantPoint {

	private Map<LaneSegment, Set<LaneSegmentLink>> constraints;

	public CrossingPoint() {
		constraints = new HashMap<>();
	}

	public void addLink(LaneSegment source, LaneSegment destination) {
		this.addLink(source, destination, 1, 0L);
	}

	public void addLink(LaneSegment source, LaneSegment destination, Long crossingTime) {
		this.addLink(source, destination, 1, crossingTime);
	}
	
	public void addLink(LaneSegment source, LaneSegment destination, int weight, Long crossingTime) {
		LaneSegmentLink link = new LaneSegmentLink(destination, weight);
		link.setCrossingTime(crossingTime);
		this.addLink(source, link);
	}
	
	public void addLink(LaneSegment source, LaneSegment destination, int weight, Distribution crossingTime) {
		LaneSegmentLink link = new LaneSegmentLink(destination, weight);
		link.setCrossingTime(crossingTime);
		this.addLink(source, link);
	}

	public void addLink(LaneSegment source, LaneSegmentLink destination) {
		Set<LaneSegmentLink> pdf = constraints.computeIfAbsent(source, ls -> new HashSet<>());
		pdf.add(destination);
		source.setDestination(this);
		destination.getDestination().setSource(this);
	}

	@Override
	public Set<LaneSegment> getSources() {
		return constraints.keySet();
	}

	@Override
	public Set<LaneSegment> getDestinations() {
		Set<LaneSegment> result = new HashSet<>();
		for (Entry<LaneSegment, Set<LaneSegmentLink>> src : constraints.entrySet()) {
			for (LaneSegmentLink link : src.getValue()) {
				result.add(link.getDestination());
			}
		}
		return result;
	}
}
