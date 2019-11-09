package tramways.model;

import java.util.List;

import tramways.model.persistable.configurations.Configuration;
import tramways.model.properties.Property;

public interface Configurable {

	public void apply(Configuration conf);
	public void apply(List<Property> properties);
	public Object getProperty(String name);

}
