package tramways.model.persistable.projects;

import javax.persistence.Embeddable;
import javax.persistence.Lob;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import tramways.dto.mappers.Json2RoadMapDtoMapper;
import tramways.dto.mappers.PropertyAdapterFactory;
import tramways.model.persistable.BaseEmbeddable;
import tramways.model.roadmap.RoadMap;

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
	
	public RoadMap getContent() {
		Json2RoadMapDtoMapper mapper = new Json2RoadMapDtoMapper();
		return mapper.map(this.map);
	}

	@Override
	public int compareTo(RoadMapWrapper other) {
		if (other == null) {
			return -1;
		}

		return name.compareTo(other.name);
	}
}
