package tramways.dto.points;

import tramways.dto.distributions.RealDistributionDto;

public class CarSourcePointDto extends SourcePointDto {

	private RealDistributionDto arrivalRate;

	public void setArrivalRate(RealDistributionDto arrivalRate) {
		this.arrivalRate = arrivalRate;
	}

	public RealDistributionDto getArrivalRate() {
		return arrivalRate;
	}
}
