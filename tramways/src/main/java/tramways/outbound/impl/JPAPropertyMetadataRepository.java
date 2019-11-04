package tramways.outbound.impl;

import java.util.List;

import tramways.model.persistable.properties.PropertyMetadata;
import tramways.outbound.AbstractJPARepository;
import tramways.outbound.PropertyMetadataRepository;

public class JPAPropertyMetadataRepository extends AbstractJPARepository<PropertyMetadata>
		implements PropertyMetadataRepository {

	@Override
	public List<PropertyMetadata> findAll() {
		return super.findAll();
	}

	@Override
	public PropertyMetadata findByUuid(String uuid) {
		return super.findByUuid(uuid);
	}

	@Override
	public PropertyMetadata create(PropertyMetadata conf) {
		persist(conf);
		return conf;
	}

	@Override
	public void delete(String uuid) {
		super.delete(findByUuid(uuid));
	}

	@Override
	protected Class<PropertyMetadata> getEntityClass() {
		return PropertyMetadata.class;
	}

}
