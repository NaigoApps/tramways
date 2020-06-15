package tramways.math.distributions;

import java.math.BigDecimal;

import org.oristool.models.stpn.trees.StochasticTransitionFeature;
import org.oristool.petrinet.TransitionFeature;
import tramways.inbound.model.Distribution;
import tramways.inbound.model.ExponentialDistribution;
import tramways.inbound.model.UniformDistribution;

public class DistributionMapper {

    private DistributionMapper() {

    }

    public static StochasticTransitionFeature map(UniformDistribution u) {
        return StochasticTransitionFeature.newUniformInstance(u.getLeft(), u.getRight());
    }

    public static StochasticTransitionFeature map(ExponentialDistribution d) {
        return StochasticTransitionFeature.newExponentialInstance(d.getLambda());
    }

    public static TransitionFeature map(Distribution distribution) {
        if (distribution instanceof ExponentialDistribution) {
            return map((ExponentialDistribution) distribution);
        } else if (distribution instanceof UniformDistribution) {
            return map((UniformDistribution) distribution);
        }
        return null;
    }
}
