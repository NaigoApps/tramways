package tramways.model.projects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import tramways.model.BaseEntity;
import tramways.model.auth.User;

@Entity
@Table(name = "projects")
public class Project extends BaseEntity {

	private String name;

	@ManyToOne
	private User owner;

	@Lob
	@Column(name = "raw_map")
	private String map;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getMap() {
		return map;
	}
	
	public void setMap(String map) {
		this.map = map;
	}
	
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
}
