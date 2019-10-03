package tramways.inbound;

import java.util.List;

import tramways.model.Configuration;

public interface ConfigurationService {

	public List<Configuration> listConfigurations();
	public Configuration getConfiguration(String uuid);
	public Configuration createConfiguration(Configuration conf);
	public void deleteConfiguration(String uuid);
	
}
