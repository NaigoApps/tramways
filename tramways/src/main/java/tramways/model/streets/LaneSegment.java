package tramways.model.streets;

import tramways.model.Identifiable;
import tramways.model.points.RelevantPoint;

public class LaneSegment extends Identifiable {

	private RelevantPoint source;
	private RelevantPoint destination;
	
	private Double length;
	
	private Double vehicleMaxSpeed;
	private Double vehicleMinGap;
	private Double vehicleLength;

	public RelevantPoint getSource() {
		return source;
	}

	public void setSource(RelevantPoint source) {
		this.source = source;
	}

	public RelevantPoint getDestination() {
		return destination;
	}

	public void setDestination(RelevantPoint destination) {
		this.destination = destination;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Double getVehicleMaxSpeed() {
		return vehicleMaxSpeed;
	}

	public void setVehicleMaxSpeed(Double vehicleMaxSpeed) {
		this.vehicleMaxSpeed = vehicleMaxSpeed;
	}

	public Double getVehicleMinGap() {
		return vehicleMinGap;
	}

	public void setVehicleMinGap(Double vehicleMinGap) {
		this.vehicleMinGap = vehicleMinGap;
	}

	public Double getVehicleLength() {
		return vehicleLength;
	}

	public void setVehicleLength(Double vehicleLength) {
		this.vehicleLength = vehicleLength;
	}
	
}
