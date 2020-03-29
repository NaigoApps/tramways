package tramways.core.model.persistable;

import java.util.UUID;

import javax.persistence.MappedSuperclass;

import tramways.core.model.Identifiable;

@MappedSuperclass
public abstract class BaseEmbeddable implements Identifiable {

	private String uuid;

	public BaseEmbeddable() {
		uuid = UUID.randomUUID().toString();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
