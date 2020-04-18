package tramways.core.model.persistable.configurations;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import tramways.core.model.persistable.BaseEntity;

@Entity
@Table(name = "configurations")
public class Configuration extends BaseEntity {

	private String name;

	@ElementCollection
	@JoinTable(name = "configuration_properties", joinColumns = { @JoinColumn(name = "configuration_id") })
	private List<PropertyWrapper> properties;

	public List<PropertyWrapper> getProperties() {
		return properties;
	}

	public void setProperties(List<PropertyWrapper> properties) {
		this.properties = properties;
	}

	public Long getIntegerProperty(String name) {
		return getProperty(name, Long.class);
	}

	public Double getDecimalProperty(String name) {
		return getProperty(name, Double.class);
	}

	public String getStringProperty(String name) {
		return getProperty(name, String.class);
	}

	private <T> T getProperty(String name, Class<T> valueClass) {
		return properties.stream()
			.filter(prop -> name.equals(prop.getName()))
			.filter(prop -> valueClass.isInstance(prop.getValue()))
			.map(prop -> valueClass.cast(prop.getValue()))
			.findFirst()
			.orElse(null);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
