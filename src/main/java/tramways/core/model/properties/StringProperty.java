package tramways.core.model.properties;

public class StringProperty extends Property {

	private String value;

	public StringProperty() {
		super(null, PropertyType.STRING);
	}
	
	public StringProperty(String name) {
		super(name, PropertyType.STRING);
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}

}
