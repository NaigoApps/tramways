package tramways.dto.mappers;

import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import tramways.core.model.distributionss.ConstantDistribution;
import tramways.core.model.distributionss.Distribution;
import tramways.core.model.distributionss.ExponentialDistribution;
import tramways.core.model.distributionss.UniformDistribution;

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
