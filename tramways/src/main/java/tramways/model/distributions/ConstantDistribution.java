package tramways.model.distributions;

import javax.persistence.Embeddable;

import org.apache.commons.math3.distribution.ConstantRealDistribution;
import org.apache.commons.math3.distribution.RealDistribution;

@Embeddable
public class ConstantDistribution implements Distribution {
	private Double value;

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public RealDistribution getRealDistribution() {
		return new ConstantRealDistribution(value);
	}
	
	@Override
	public void accept(DistributionVisitor visitor) {
		visitor.visit(this);
	}
}
