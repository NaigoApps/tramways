package tramways.dto.distributions;

public class UniformDistributionDto implements DistributionDto {
	private Double min;
	private Double max;

	public Double getMin() {
		return min;
	}

	public void setMin(Double min) {
		this.min = min;
	}

	public Double getMax() {
		return max;
	}

	public void setMax(Double max) {
		this.max = max;
	}
	
	@Override
	public void accept(DistributionDtoVisitor visitor) {
		visitor.visit(this);
	}
}
