package tramways.model.roadmap.points.trafficlight;

import java.util.HashSet;
import java.util.Set;

import tramways.model.roadmap.lanes.LaneSegment;

public class SensorTrafficLight implements TrafficLight{

	private Set<LaneSegment> activators;
	
	private int anticipation;
	
	public SensorTrafficLight() {
		activators = new HashSet<>();
	}

	public int getAnticipation() {
		return anticipation;
	}

	public void setAnticipation(int anticipation) {
		this.anticipation = anticipation;
	}
	
	public void addActivator(LaneSegment segment) {
		activators.add(segment);
	}

}
