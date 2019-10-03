package tramways.dto;

import java.util.List;

import tramways.dto.properties.PropertyWrapper;

public class ConfigurationDto extends Dto{

	private String name;

	private List<PropertyWrapper> properties;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	<T> T getProperty(String name, Class<T> valueClass) {
		for (PropertyWrapper prop : properties) {
			if (prop.getName().equals(name)) {
				return valueClass.cast(prop.getValue());
			}
		}
		return null;
	}
}
