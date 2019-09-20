package tramways.dto.properties;

import tramways.dto.distributions.DistributionDto;

public class DistributionPropertyDto extends PropertyDto {

	private DistributionDto value;

	public void setValue(DistributionDto value) {
		this.value = value;
	}

	@Override
	public DistributionDto getValue() {
		return value;
	}

	@Override
	public void accept(PropertyDtoVisitor visitor) {
		visitor.visit(this);
	}
}
