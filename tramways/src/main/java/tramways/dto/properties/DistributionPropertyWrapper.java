package tramways.dto.properties;

import tramways.model.distributions.Distribution;
import tramways.model.properties.PropertyType;

public class DistributionPropertyWrapper extends PropertyWrapper {

	private Distribution value;

	public DistributionPropertyWrapper() {
		setType(PropertyType.DISTRIBUTION);
	}
	
	public void setValue(Distribution value) {
		this.value = value;
	}

	@Override
	public Distribution getValue() {
		return value;
	}

}
