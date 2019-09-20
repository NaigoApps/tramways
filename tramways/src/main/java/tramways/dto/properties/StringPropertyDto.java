package tramways.dto.properties;

public class StringPropertyDto extends PropertyDto {

	private String value;

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void accept(PropertyDtoVisitor visitor) {
		visitor.visit(this);
	}
}
