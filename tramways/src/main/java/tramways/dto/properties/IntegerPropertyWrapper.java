package tramways.dto.properties;

import tramways.model.properties.PropertyType;

public class IntegerPropertyWrapper extends PropertyWrapper {

	private Long value;
	
	public IntegerPropertyWrapper() {
		setType(PropertyType.INTEGER);
	}

	public void setValue(Long value) {
		this.value = value;
	}

	@Override
	public Long getValue() {
		return value;
	}
	
}