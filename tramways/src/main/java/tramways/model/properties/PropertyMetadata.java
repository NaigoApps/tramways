package tramways.model.properties;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import tramways.model.BaseEntity;

@Entity
@Table(name = "property_type")
public class PropertyMetadata extends BaseEntity {

	@Enumerated(EnumType.STRING)
	private PropertyType type;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PropertyType getType() {
		return type;
	}

	public void setType(PropertyType type) {
		this.type = type;
	}

}
