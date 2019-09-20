package tramways.model.points;

import org.apache.commons.math3.distribution.ConstantRealDistribution;
import org.apache.commons.math3.distribution.RealDistribution;

public class TramSourcePoint extends SourcePoint {

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
