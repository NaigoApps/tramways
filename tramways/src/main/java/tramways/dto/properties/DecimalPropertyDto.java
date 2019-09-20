package tramways.dto.properties;

public class DecimalPropertyDto extends PropertyDto {

	private Double value;

	@Override
	public Double getValue() {
		return value;
	}

	@Override
	public void accept(PropertyDtoVisitor visitor) {
		visitor.visit(this);
	}
}
