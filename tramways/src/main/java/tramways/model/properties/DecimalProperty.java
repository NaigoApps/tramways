package tramways.model.properties;

public class DecimalProperty extends Property {

	private Double value;

	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public Double getValue() {
		return value;
	}

	@Override
	public void accept(PropertyVisitor visitor) {
		visitor.visit(this);
	}
}
