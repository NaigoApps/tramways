package tramways.model.roadmap.points;

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
}
