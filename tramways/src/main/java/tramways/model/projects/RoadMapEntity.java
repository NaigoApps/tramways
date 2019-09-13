package tramways.model.projects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
public class RoadMapEntity {

	@Lob
	@Column(name = "raw_map")
	private String rawMap;

	public String getRawMap() {
		return rawMap;
	}

	public void setRawMap(String rawMap) {
		this.rawMap = rawMap;
	}
}
