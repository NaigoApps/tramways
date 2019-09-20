package tramways.dto;

import java.util.List;

import tramways.dto.distributions.DistributionDto;
import tramways.dto.properties.PropertyDto;

public class ConfigurableDto extends Dto {

	private List<PropertyDto> properties;

	public List<PropertyDto> getProperties() {
		return properties;
	}

	public void setProperties(List<PropertyDto> properties) {
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

	public DistributionDto getDistributionProperty(String name) {
		return getProperty(name, DistributionDto.class);
	}

	<T> T getProperty(String name, Class<T> valueClass) {
		for (PropertyDto prop : properties) {
			if (prop.getName().equals(name)) {
				return valueClass.cast(prop.getValue());
			}
		}
		return null;
	}
}
