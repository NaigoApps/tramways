package tramways.model;

import java.util.ArrayList;
import java.util.List;

import tramways.model.distributions.Distribution;
import tramways.model.persistable.configurations.Configuration;
import tramways.model.persistable.properties.PropertyWrapper;

public class AbstractConfigurable extends AbstractIdentifiable implements Configurable {

	private List<PropertyWrapper> properties;

	public AbstractConfigurable() {
		properties = new ArrayList<>();
	}

	public void apply(Configuration conf) {
		for (PropertyWrapper prop : conf.getProperties()) {
			applyImpl(prop);
		}
	}

	public void apply(PropertyWrapper prop) {
		applyImpl(prop);
	}

	private void applyImpl(PropertyWrapper prop) {
		int index = -1;
		for (int i = 0; i < properties.size(); i++) {
			if (properties.get(i).getName().equals(prop.getName())) {
				index = i;
			}
		}
		if (index != -1) {
			properties.remove(index);
		}
		properties.add(prop);
	}

	public Long getIntegerProperty(String name) {
		return getTypedProperty(name, Long.class);
	}

	public Double getDecimalProperty(String name) {
		return getTypedProperty(name, Double.class);
	}

	public String getStringProperty(String name) {
		return getTypedProperty(name, String.class);
	}

	public Distribution getDistributionProperty(String name) {
		return getTypedProperty(name, Distribution.class);
	}

	private <T> T getTypedProperty(String name, Class<T> type) {
		Object prop = getProperty(name);
		if (type.isInstance(prop)) {
			return type.cast(prop);
		}
		return null;
	}

	public Object getProperty(String name) {
		return properties.stream().filter(prop -> prop.getName().equals(name)).map(PropertyWrapper::getValue).findFirst()
				.orElse(null);
	}

}
