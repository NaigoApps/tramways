package tramways.dto;

import java.util.UUID;

public class Dto {

	private Long id;
	private String uuid;
	
	public Dto() {
		uuid = UUID.randomUUID().toString();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return uuid.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Dto) {
			return ((Dto) obj).getUuid().equals(uuid);
		}
		return false;
	}
}
