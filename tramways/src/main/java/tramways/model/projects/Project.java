package tramways.model.projects;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import tramways.model.BaseEntity;
import tramways.model.auth.User;

@Entity
@Table(name = "projects")
public class Project extends BaseEntity {
	
	@ManyToOne
	private User owner;

	@Embedded
	private RoadMap map;
	
	public RoadMap getMap() {
		return map;
	}
	
	public void setMap(RoadMap map) {
		this.map = map;
	}
}
