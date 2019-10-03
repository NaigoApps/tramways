package tramways.dto.properties;

public class DecimalPropertyDto extends PropertyDto {

	private Double value;

	public void setValue(Double value) {
		this.value = value;
	}

	public Double getValue() {
		return value;
	}

}
