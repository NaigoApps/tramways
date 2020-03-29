package tramways.core.model.properties;

public class IntegerProperty extends Property {

	private Long value;
	
	public IntegerProperty() {
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