package tramways.outbound;

import java.util.List;

import tramways.model.persistable.properties.PropertyMetadata;

public interface PropertyMetadataRepository {

	public List<PropertyMetadata> findAll();
	public PropertyMetadata findByUuid(String uuid);
	public PropertyMetadata create(PropertyMetadata metadata);
	public void delete(String uuid);
	
}
