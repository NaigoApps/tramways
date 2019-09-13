package tramways.model.points;

import org.apache.commons.math3.distribution.ConstantRealDistribution;
import org.apache.commons.math3.distribution.RealDistribution;

import tramways.model.streets.TramLaneSegment;

public class TramSourcePoint extends SourcePoint<TramLaneSegment> {

	private int period;

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	@Override
	public RealDistribution getRate() {
		return new ConstantRealDistribution(period);
	}
}
