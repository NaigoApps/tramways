package tramways.dto.points;

public abstract class SourcePointDto extends RelevantPointDto {

	private String targetLane;

	public String getTargetLane() {
		return targetLane;
	}

	public void setTargetLane(String targetLane) {
		this.targetLane = targetLane;
	}
}
