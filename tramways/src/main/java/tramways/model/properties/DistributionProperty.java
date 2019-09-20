package tramways.model.properties;

import javax.persistence.Embeddable;

import tramways.model.distributions.Distribution;

@Embeddable
public class DistributionProperty extends Property {

	private Distribution value;

	public void setValue(Distribution value) {
		this.value = value;
	}

	@Override
	public Distribution getValue() {
		return value;
	}

	@Override
	public void accept(PropertyVisitor visitor) {
		visitor.visit(this);
	}
}
