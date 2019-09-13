package tramways.dto.lanes;

import tramways.dto.Dto;

public class LaneSegmentDto extends Dto {
	private String source;
	private String destination;

	private Double length;

	private Double vehicleMaxSpeed;
	private Double vehicleMinGap;
	private Double vehicleLength;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
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
