package tramways.outbound;

import java.util.List;

import tramways.core.model.persistable.configurations.Configuration;

public interface ConfigurationRepository {

	List<Configuration> findAll();
	Configuration findByUuid(String uuid);
	List<Configuration> findByCategory(String category);
	Configuration create(Configuration conf);
	void delete(String uuid);

}
