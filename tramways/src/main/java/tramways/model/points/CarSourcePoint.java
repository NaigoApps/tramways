package tramways.model.points;

import org.apache.commons.math3.distribution.RealDistribution;

public class CarSourcePoint extends SourcePoint {

	private RealDistribution arrivalRate;
	
	public void setArrivalRate(RealDistribution arrivalRate) {
		this.arrivalRate = arrivalRate;
	}
	
	@Override
	public RealDistribution getRate() {
		return arrivalRate;
	}
}
