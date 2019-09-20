package tramways.dto.properties;

public class IntegerPropertyDto extends PropertyDto {

	private Long value;

	public void setValue(Long value) {
		this.value = value;
	}

	@Override
	public Long getValue() {
		return value;
	}

	@Override
	public void accept(PropertyDtoVisitor visitor) {
		visitor.visit(this);
	}
}
