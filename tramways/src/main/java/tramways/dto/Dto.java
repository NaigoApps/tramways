package tramways.dto;

public class Dto {
	private String uuid;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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
