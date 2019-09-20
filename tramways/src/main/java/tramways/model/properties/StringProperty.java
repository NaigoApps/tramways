package tramways.model.properties;

import javax.persistence.Embeddable;

@Embeddable
public class StringProperty extends Property {

	private String value;

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void accept(PropertyVisitor visitor) {
		visitor.visit(this);
	}
}
