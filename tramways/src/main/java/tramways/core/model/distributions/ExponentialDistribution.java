package tramways.core.model.distributions;

import javax.persistence.Embeddable;

import org.apache.commons.math3.distribution.RealDistribution;

@Embeddable
public class ExponentialDistribution implements Distribution {
	private Double mean;

	public Double getMean() {
		return mean;
	}

	public void setMean(Double mean) {
		this.mean = mean;
	}
	
	@Override
	public RealDistribution getRealDistribution() {
		return new org.apache.commons.math3.distribution.ExponentialDistribution(mean);
	}
	
	@Override
	public void accept(DistributionVisitor visitor) {
		visitor.visit(this);
	}
}
