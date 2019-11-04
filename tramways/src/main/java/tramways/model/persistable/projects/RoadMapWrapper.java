package tramways.model.persistable.projects;

import javax.persistence.Embeddable;
import javax.persistence.Lob;

import tramways.model.persistable.BaseEmbeddable;

@Embeddable
public class RoadMapWrapper extends BaseEmbeddable implements Comparable<RoadMapWrapper> {

	private String name;

	@Lob
	private String map;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	@Override
	public int compareTo(RoadMapWrapper other) {
		if (other == null) {
			return -1;
		}

		return name.compareTo(other.name);
	}
}
