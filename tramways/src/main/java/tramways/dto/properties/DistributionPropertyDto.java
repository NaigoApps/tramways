package tramways.dto.properties;

import tramways.dto.distributions.DistributionDto;

public class DistributionPropertyDto extends PropertyDto {

	private DistributionDto value;

	public void setValue(DistributionDto value) {
		this.value = value;
	}

	public DistributionDto getValue() {
		return value;
	}

}
