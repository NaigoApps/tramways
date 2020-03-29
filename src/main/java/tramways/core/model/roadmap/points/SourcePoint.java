package tramways.core.model.roadmap.points;

import tramways.core.model.roadmap.graph.Arc;

import java.util.List;

public class SourcePoint extends RelevantPoint {

	private SourcePointType kind;

	private String targetLane;

	public String getTargetLane() {
		return targetLane;
	}

	public void setTargetLane(String targetLane) {
		this.targetLane = targetLane;
	}

	public SourcePointType getKind() {
		return kind;
	}

	public void setKind(SourcePointType kind) {
		this.kind = kind;
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
