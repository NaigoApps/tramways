package tramways.core.model.persistable.projects;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import tramways.core.model.persistable.BaseEntity;
import tramways.core.model.roadmap.RoadMap;
import tramways.dto.mappers.Json2RoadMapDtoMapper;

@Entity
@Table(name = "road_maps")
public class RoadMapWrapper extends BaseEntity implements Comparable<RoadMapWrapper> {

	private String name;

	@Lob
	private String map;

	@OneToMany(cascade = CascadeType.ALL)
	private List<AnalysisWrapper> analysis;

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

	public List<AnalysisWrapper> getAnalysis() {
		return analysis;
	}

	public void setAnalysis(List<AnalysisWrapper> analysis) {
		this.analysis = analysis;
	}

	public void addAnalysis(AnalysisWrapper analysis) {
		this.analysis.add(analysis);
	}

	public void removeAnalysis(String id) {
		this.analysis.removeIf(a -> a.getUuid().equals(id));
	}
	
	@Override
	public int compareTo(RoadMapWrapper other) {
		if (other == null) {
			return -1;
		}

		return name.compareTo(other.name);
	}

}
