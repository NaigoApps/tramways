package tramways.model.properties;

public class StringProperty extends Property {

	private String value;

	public StringProperty() {
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
