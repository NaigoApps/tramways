package tramways.dto.distributions;

public class ConstantDistributionDto implements RealDistributionDto {
	private Double value;

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}
