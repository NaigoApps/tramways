package tramways.dto.properties;

import tramways.model.properties.PropertyType;

public class StringPropertyWrapper extends PropertyWrapper {

	private String value;

	public StringPropertyWrapper() {
		setType(PropertyType.STRING);
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}

}
