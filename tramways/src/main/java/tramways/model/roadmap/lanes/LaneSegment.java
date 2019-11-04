package tramways.model.roadmap.lanes;

import tramways.model.AbstractConfigurable;
import tramways.model.persistable.properties.PropertyWrapper;
import tramways.model.roadmap.points.RelevantPoint;

public class LaneSegment extends AbstractConfigurable {

	private static final String LENGTH_PROPERTY = "length";
	private static final String MAX_SPEED_PROPERTY = "maxSpeed";
	private static final String MIN_GAP_PROPERTY = "minGap";
	private static final String VEHICLE_LENGTH_PROPERTY = "vehicleLength";
	
	private LaneSegmentType type;
	
	private RelevantPoint source;
	
	private RelevantPoint destination;
	
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
		return getDecimalProperty(LENGTH_PROPERTY);
	}

	public void setLength(Double length) {
		apply(PropertyWrapper.create(LENGTH_PROPERTY, length));
	}

	public Double getVehicleMaxSpeed() {
		return getDecimalProperty(MAX_SPEED_PROPERTY);
	}

	public void setVehicleMaxSpeed(Double vehicleMaxSpeed) {
		apply(PropertyWrapper.create(MAX_SPEED_PROPERTY, vehicleMaxSpeed));
	}

	public Double getVehicleMinGap() {
		return getDecimalProperty(MIN_GAP_PROPERTY);
	}

	public void setVehicleMinGap(Double vehicleMinGap) {
		apply(PropertyWrapper.create(MIN_GAP_PROPERTY, vehicleMinGap));
	}

	public Double getVehicleLength() {
		return getDecimalProperty(VEHICLE_LENGTH_PROPERTY);
	}

	public void setVehicleLength(Double vehicleLength) {
		apply(PropertyWrapper.create(VEHICLE_LENGTH_PROPERTY, vehicleLength));
	}
	
	public LaneSegmentType getType() {
		return type;
	}
	
	public void setType(LaneSegmentType type) {
		this.type = type;
	}
	
}
