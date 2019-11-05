package tramways.model.persistable.properties;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import tramways.model.persistable.BaseEntity;
import tramways.model.properties.PropertyType;

@Entity
@Table(name = "property_type")
public class PropertyMetadata extends BaseEntity {

	@Enumerated(EnumType.STRING)
	private PropertyType type;
	
	private String name;
	
	public PropertyMetadata() {
	}

	public PropertyMetadata(String name, PropertyType type) {
		this.name = name;
		this.type = type;
	}
	
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
