package tramways.model.properties;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = IntegerProperty.class, name = "integer"),
		@Type(value = DecimalProperty.class, name = "decimal"),
		@Type(value = StringProperty.class, name = "string"),
		@Type(value = DistributionProperty.class, name = "distribution") })
public abstract class Property {

	private String type;
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	protected void setType(PropertyType type) {
		this.type = type.name();
	}

	public PropertyType getType() {
		return PropertyType.valueOf(type);
	}

	public abstract Object getValue();

	
}
