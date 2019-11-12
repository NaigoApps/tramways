package tramways.core.model.distributions;

public interface DistributionVisitor {

	void visit(ConstantDistribution constantDistribution);

	void visit(ExponentialDistribution exponentialDistribution);

	void visit(UniformDistribution uniformDistribution);

}
