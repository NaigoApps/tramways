package tramways.core.model.persistable.configurations;

import tramways.core.model.persistable.BaseEntity;
import tramways.dto.mappers.Json2PropertyMapper;
import tramways.inbound.model.Property;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "configurations")
public class Configuration extends BaseEntity {

	private String category;
	private String name;

	@Lob
	private String properties;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Property> getProperties() {
		return new Json2PropertyMapper().mapProperties(properties);
	}

	public void setProperties(List<Property> properties) {
		this.properties = new Json2PropertyMapper().map(properties);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
