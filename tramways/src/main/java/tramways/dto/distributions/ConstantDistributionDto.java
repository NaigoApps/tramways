package tramways.dto.distributions;

public class ConstantDistributionDto implements DistributionDto {
	private Double value;

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
	
	@Override
	public void accept(DistributionDtoVisitor visitor) {
		visitor.visit(this);
	}
}
