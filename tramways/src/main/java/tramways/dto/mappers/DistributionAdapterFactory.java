package tramways.dto.mappers;

import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import tramways.model.distributions.ConstantDistribution;
import tramways.model.distributions.Distribution;
import tramways.model.distributions.ExponentialDistribution;
import tramways.model.distributions.UniformDistribution;

public class DistributionAdapterFactory {
	
	private DistributionAdapterFactory() {
	}
	
	public static RuntimeTypeAdapterFactory<Distribution> getFactory(){
		return RuntimeTypeAdapterFactory
				.of(Distribution.class)
				.registerSubtype(ConstantDistribution.class, "constant")
				.registerSubtype(UniformDistribution.class, "uniform")
				.registerSubtype(ExponentialDistribution.class, "exponential");
	}
	
}
