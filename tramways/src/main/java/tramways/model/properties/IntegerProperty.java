package tramways.model.properties;

import javax.persistence.Embeddable;

@Embeddable
public class IntegerProperty extends Property {

	private Long value;

	public void setValue(Long value) {
		this.value = value;
	}

	@Override
	public Long getValue() {
		return value;
	}

	@Override
	public void accept(PropertyVisitor visitor) {
		visitor.visit(this);
	}
}
