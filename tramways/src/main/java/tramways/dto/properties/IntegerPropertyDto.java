package tramways.dto.properties;

public class IntegerPropertyDto extends PropertyDto {

	private Long value;

	public void setValue(Long value) {
		this.value = value;
	}

	public Long getValue() {
		return value;
	}
	
}