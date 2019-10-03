package tramways.inbound.impl;

import java.util.List;

import javax.inject.Inject;

import tramways.inbound.ConfigurationService;
import tramways.model.Configuration;
import tramways.outbound.ConfigurationRepository;

public class ConfigurationServiceImpl implements ConfigurationService {

	@Inject
	private ConfigurationRepository repository;
	
	@Override
	public List<Configuration> listConfigurations() {
		return repository.findAll();
	}

	@Override
	public Configuration getConfiguration(String uuid) {
		return repository.findByUuid(uuid);
	}
	
	@Override
	public Configuration createConfiguration(Configuration conf) {
		conf.assignUuid();
		return repository.create(conf);
	}

	@Override
	public void deleteConfiguration(String uuid) {
		repository.delete(uuid);
	}

}
