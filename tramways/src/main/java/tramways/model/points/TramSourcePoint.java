package tramways.model.points;

import org.apache.commons.math3.distribution.ConstantRealDistribution;
import org.apache.commons.math3.distribution.RealDistribution;

import tramways.model.properties.Property;

public class TramSourcePoint extends SourcePoint {

	private static final String PERIOD_PROPERTY = "period";

	public Long getPeriod() {
		return getIntegerProperty(PERIOD_PROPERTY);
	}

	public void setPeriod(Long period) {
		apply(Property.create(PERIOD_PROPERTY, period));
	}

	@Override
	public RealDistribution getRate() {
		return new ConstantRealDistribution(getPeriod());
	}
}
