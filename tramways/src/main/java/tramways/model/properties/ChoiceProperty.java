package tramways.model.properties;

public class ChoiceProperty extends Property {

	private String value;

	public ChoiceProperty() {
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
