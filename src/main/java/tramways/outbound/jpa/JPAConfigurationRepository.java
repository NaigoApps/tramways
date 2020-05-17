package tramways.outbound.jpa;

import java.util.Collections;
import java.util.List;

import org.slf4j.LoggerFactory;
import tramways.core.model.persistable.configurations.Configuration;
import tramways.core.model.persistable.configurations.Configuration_;
import tramways.outbound.AbstractJPARepository;
import tramways.outbound.ConfigurationRepository;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
    public List<Configuration> findByCategory(String category) {
		try {
			CriteriaQuery<Configuration> query = query();
			Root<Configuration> root = query.from(getEntityClass());
			query.where(cb().equal(root.get(Configuration_.category), category));
			return getEntityManager().createQuery(query).getResultList();
		} catch (Exception ex) {
			LoggerFactory.getLogger(getClass()).error("Error", ex);
		}
		return Collections.emptyList();
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
