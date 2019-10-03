package tramways.dto.properties;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import tramways.model.properties.PropertyType;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = IntegerPropertyWrapper.class, name = "integer"),
		@Type(value = DecimalPropertyWrapper.class, name = "decimal"),
		@Type(value = StringPropertyWrapper.class, name = "string"),
		@Type(value = DistributionPropertyWrapper.class, name = "distribution") })
public abstract class PropertyWrapper {

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
