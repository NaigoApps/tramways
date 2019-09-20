package tramways.model.distributions;

import javax.persistence.Embeddable;

import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;

@Embeddable
public class UniformDistribution implements Distribution {
	private Double min;
	private Double max;

	public Double getMin() {
		return min;
	}

	public void setMin(Double min) {
		this.min = min;
	}

	public Double getMax() {
		return max;
	}

	public void setMax(Double max) {
		this.max = max;
	}
	
	@Override
	public RealDistribution getRealDistribution() {
		return new UniformRealDistribution(min, max);
	}
	
	@Override
	public void accept(DistributionVisitor visitor) {
		visitor.visit(this);
	}
}
