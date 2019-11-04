package tramways.model.properties;

public class DecimalProperty extends Property {

	private Double value;

	public DecimalProperty() {
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
