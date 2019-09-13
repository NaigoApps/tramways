package tramways.dto.distributions;

public class ExponentialDistributionDto implements RealDistributionDto {
	private Double mean;

	public Double getMean() {
		return mean;
	}

	public void setMean(Double mean) {
		this.mean = mean;
	}
}
