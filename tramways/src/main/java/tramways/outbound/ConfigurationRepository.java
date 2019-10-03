package tramways.outbound;

import java.util.List;

import tramways.model.Configuration;

public interface ConfigurationRepository {

	public List<Configuration> findAll();
	public Configuration findByUuid(String uuid);
	public Configuration create(Configuration conf);
	public void delete(String uuid);
	
}
