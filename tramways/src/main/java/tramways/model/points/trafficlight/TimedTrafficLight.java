package tramways.model.points.trafficlight;

public class TimedTrafficLight implements TrafficLight{

	private boolean startsOnGreen;

	private int redDuration;
	private int greenDuration;

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
