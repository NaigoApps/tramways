package tramways.outbound.impl;

import java.util.List;

import tramways.model.persistable.configurations.Configuration;
import tramways.outbound.AbstractJPARepository;
import tramways.outbound.ConfigurationRepository;

public class JPAConfigurationRepository extends AbstractJPARepository<Configuration> implements ConfigurationRepository {

	@Override
	public List<Configuration> findAll() {
		return super.findAll();
	}
	
	@Override
	public Configuration findByUuid(String uuid) {
		return super.findByUuid(uuid);
	}

	@Override
	public Configuration create(Configuration conf) {
		persist(conf);
		return conf;
	}

	@Override
	public void delete(String uuid) {
		super.delete(findByUuid(uuid));
	}
	
	@Override
	protected Class<Configuration> getEntityClass() {
		return Configuration.class;
	}

}
