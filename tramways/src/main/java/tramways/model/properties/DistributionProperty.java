package tramways.model.properties;

import tramways.model.distributions.Distribution;

public class DistributionProperty extends Property {

	private Distribution value;

	public DistributionProperty() {
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
