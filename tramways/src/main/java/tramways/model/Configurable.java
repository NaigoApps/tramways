package tramways.model;

import tramways.model.persistable.configurations.Configuration;

public interface Configurable {

	public void apply(Configuration conf);
	public Object getProperty(String name);

}
