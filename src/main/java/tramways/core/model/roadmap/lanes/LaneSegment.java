package tramways.core.model.roadmap.lanes;

import tramways.core.model.AbstractConfigurable;
import tramways.core.model.roadmap.graph.Arc;
import tramways.core.model.roadmap.points.RelevantPoint;

public class LaneSegment extends AbstractConfigurable implements Arc {
	
	private RelevantPoint source;
	private RelevantPoint destination;

	@Override
	public RelevantPoint getSource() {
		return source;
	}

	public void setSource(RelevantPoint source) {
		this.source = source;
	}

	@Override
	public RelevantPoint getDestination() {
		return destination;
	}

	public void setDestination(RelevantPoint destination) {
		this.destination = destination;
	}

}
