package tramways.model.projects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import tramways.model.BaseEntity;

@Entity
@Table(name = "raw_maps")
public class RawMap extends BaseEntity implements Comparable<RawMap> {

	private String name;

	@Lob
	private String map;

	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;

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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public int compareTo(RawMap other) {
		if (other == null) {
			return -1;
		}

		return name.compareTo(other.name);
	}
}
