package tramways.dto.properties;

import tramways.dto.distributions.DistributionDto;
import tramways.model.distributions.Distribution;
import tramways.model.properties.PropertyType;

public class DistributionPropertyWrapper extends PropertyWrapper {

	private DistributionDto value;

	public DistributionPropertyWrapper() {
		setType(PropertyType.DISTRIBUTION);
	}
	
	public void setValue(DistributionDto value) {
		this.value = value;
	}

	@Override
	public DistributionDto getValue() {
		return value;
	}

}
