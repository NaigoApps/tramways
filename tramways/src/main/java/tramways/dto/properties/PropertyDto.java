package tramways.dto.properties;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = IntegerPropertyDto.class, name = "integer"),
		@Type(value = DecimalPropertyDto.class, name = "decimal"),
		@Type(value = StringPropertyDto.class, name = "string"),
		@Type(value = DistributionPropertyDto.class, name = "distribution") })
public abstract class PropertyDto {
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public abstract Object getValue();
}
