package tramways.core.model;

import java.util.List;

import tramways.core.model.persistable.configurations.Configuration;
import tramways.core.model.properties.Property;

public interface Configurable {

	public void apply(Configuration conf);
	public void apply(List<Property> properties);
	public Object getProperty(String name);

}
