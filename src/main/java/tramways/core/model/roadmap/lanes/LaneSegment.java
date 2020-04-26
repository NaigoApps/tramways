package tramways.core.model.roadmap.lanes;

import tramways.core.model.AbstractConfigurable;
import tramways.core.model.roadmap.graph.Arc;
import tramways.core.model.roadmap.points.NetworkPoint;

public class LaneSegment extends AbstractConfigurable implements Arc<NetworkPoint> {

	private NetworkPoint source;
	private NetworkPoint destination;

	@Override
	public NetworkPoint getSource() {
		return source;
	}

	public void setSource(NetworkPoint source) {
		this.source = source;
	}

	@Override
	public NetworkPoint getDestination() {
		return destination;
	}

	public void setDestination(NetworkPoint destination) {
		this.destination = destination;
	}

}
