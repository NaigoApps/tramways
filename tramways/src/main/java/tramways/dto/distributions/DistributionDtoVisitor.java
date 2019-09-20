package tramways.dto.distributions;

public interface DistributionDtoVisitor {

	public void visit(ConstantDistributionDto d);

	public void visit(ExponentialDistributionDto d);

	public void visit(UniformDistributionDto d);

}
