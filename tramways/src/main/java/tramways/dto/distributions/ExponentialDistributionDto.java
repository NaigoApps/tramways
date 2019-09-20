package tramways.dto.distributions;

public class ExponentialDistributionDto implements DistributionDto {
	private Double mean;

	public Double getMean() {
		return mean;
	}

	public void setMean(Double mean) {
		this.mean = mean;
	}
	
	@Override
	public void accept(DistributionDtoVisitor visitor) {
		visitor.visit(this);
	}
}
