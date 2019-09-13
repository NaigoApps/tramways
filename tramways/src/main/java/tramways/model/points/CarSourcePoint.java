package tramways.model.points;

import org.apache.commons.math3.distribution.RealDistribution;

import tramways.model.streets.CarLaneSegment;

public class CarSourcePoint extends SourcePoint<CarLaneSegment> {

	private RealDistribution arrivalRate;
	
	public void setArrivalRate(RealDistribution arrivalRate) {
		this.arrivalRate = arrivalRate;
	}
	
	@Override
	public RealDistribution getRate() {
		return arrivalRate;
	}
}
