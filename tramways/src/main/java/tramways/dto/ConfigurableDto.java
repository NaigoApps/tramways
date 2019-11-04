package tramways.dto;

import java.util.List;

import tramways.model.distributions.Distribution;
import tramways.model.properties.Property;

public class ConfigurableDto extends Dto {

	private List<Property> properties;

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
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

	public Distribution getDistributionProperty(String name) {
		return getProperty(name, Distribution.class);
	}

	<T> T getProperty(String name, Class<T> valueClass) {
		for (Property prop : properties) {
			if (prop.getName().equals(name)) {
				return valueClass.cast(prop.getValue());
			}
		}
		return null;
	}
}
