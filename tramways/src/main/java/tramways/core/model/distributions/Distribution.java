package tramways.core.model.distributions;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

import org.apache.commons.math3.distribution.RealDistribution;

@Embeddable
@MappedSuperclass
public interface Distribution {

	public RealDistribution getRealDistribution();
	public void accept(DistributionVisitor visitor);
	
}
