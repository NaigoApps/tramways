package tramways.model.roadmap.points;

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

}
