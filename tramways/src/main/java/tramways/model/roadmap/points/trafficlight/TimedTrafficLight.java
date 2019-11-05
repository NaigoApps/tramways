package tramways.model.roadmap.points.trafficlight;

public class TimedTrafficLight implements TrafficLight{

	private Boolean on;
	private Boolean startsOnGreen;

	private Integer redDuration;
	private Integer greenDuration;

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}

	public boolean isStartsOnGreen() {
		return startsOnGreen;
	}

	public void setStartsOnGreen(boolean startsOnGreen) {
		this.startsOnGreen = startsOnGreen;
	}

	public int getRedDuration() {
		return redDuration;
	}

	public void setRedDuration(int redDuration) {
		this.redDuration = redDuration;
	}

	public int getGreenDuration() {
		return greenDuration;
	}

	public void setGreenDuration(int greenDuration) {
		this.greenDuration = greenDuration;
	}

}
