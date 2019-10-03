package tramways.dto.properties;

import tramways.model.properties.PropertyType;

public class DecimalPropertyWrapper extends PropertyWrapper {

	private Double value;

	public DecimalPropertyWrapper() {
		setType(PropertyType.DECIMAL);
	}
	
	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public Double getValue() {
		return value;
	}

}
