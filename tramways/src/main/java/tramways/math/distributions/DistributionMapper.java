package tramways.math.distributions;

import java.math.BigDecimal;

import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;
import org.oristool.models.stpn.trees.StochasticTransitionFeature;

/**
 *
 * @author naigo
 */
public class DistributionMapper {

	private DistributionMapper() {
		
	}
	
	public static StochasticTransitionFeature map(UniformRealDistribution u) {
		return StochasticTransitionFeature.newUniformInstance(BigDecimal.valueOf(u.getSupportLowerBound()),
				BigDecimal.valueOf(u.getSupportUpperBound()));
	}

	public static StochasticTransitionFeature map(ExponentialDistribution d) {
		return StochasticTransitionFeature.newExponentialInstance(BigDecimal.valueOf(d.getMean()));
	}

}
