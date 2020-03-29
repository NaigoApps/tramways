package tramways.core.model.roadmap.points;

import tramways.core.model.roadmap.graph.Arc;

import java.util.ArrayList;
import java.util.List;

public class DestinationPoint extends RelevantPoint {

	private List<String> lanes;

	public DestinationPoint() {
		lanes = new ArrayList<>();
	}

	public List<String> getLanes() {
		return lanes;
	}

	public void setLanes(List<String> lanes) {
		this.lanes = lanes;
	}

	@Override
	public List<Arc> getSources() {
		//TODO
		return null;
	}

	@Override
	public List<Arc> getDestinations() {
		//TODO
		return null;
	}
}
