package tramways.model;

import java.util.ArrayList;
import java.util.List;

import tramways.dto.distributions.DistributionDto;
import tramways.model.properties.Property;

public class ConfigurableEntity extends AbstractIdentifiable {

	private List<Property> properties;
	
	public ConfigurableEntity() {
		properties = new ArrayList<>();
	}

	public void apply(Configuration conf) {
		for(Property prop : conf.getProperties()) {
			apply(prop);
		}
	}
	
	private void apply(Property prop) {
		int index = -1;
		for(int i = 0;i < properties.size();i++) {
			if(properties.get(i).getName().equals(prop.getName())) {
				index = i;
			}
		}
		if(index != -1) {
			properties.remove(index);
		}
		properties.add(prop);
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
		for (Property prop : properties) {
			if (prop.getName().equals(name)) {
				return valueClass.cast(prop.getValue());
			}
		}
		return null;
	}

}
