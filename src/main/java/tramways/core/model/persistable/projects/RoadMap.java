package tramways.core.model.persistable.projects;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import tramways.core.model.persistable.BaseEntity;
import tramways.dto.mappers.Json2RoadMapMapper;

@Entity
@Table(name = "road_maps")
public class RoadMap extends BaseEntity implements Comparable<RoadMap> {

	private String name;

	@Lob
	private String map;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Analysis> analysis;

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

	public tramways.inbound.model.RoadMap getContent() {
		Json2RoadMapMapper mapper = new Json2RoadMapMapper();
		return mapper.map(this.map);
	}

	public List<Analysis> getAnalysis() {
		return analysis;
	}

	public void setAnalysis(List<Analysis> analysis) {
		this.analysis = analysis;
	}

	public void addAnalysis(Analysis analysis) {
		this.analysis.add(analysis);
	}

	public void removeAnalysis(String id) {
		this.analysis.removeIf(a -> a.getUuid().equals(id));
	}

	@Override
	public int compareTo(RoadMap other) {
		if (other == null) {
			return -1;
		}

		return name.compareTo(other.name);
	}

}
