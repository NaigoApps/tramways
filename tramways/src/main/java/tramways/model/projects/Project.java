package tramways.model.projects;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import tramways.model.BaseEntity;
import tramways.model.auth.User;

@Entity
@Table(name = "projects")
public class Project extends BaseEntity {

	private String name;

	@ManyToOne
	private User owner;

	@OneToMany(mappedBy = "project")
	private Set<RawMap> maps;

	public Project() {
		maps = new HashSet<>();
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Set<RawMap> getMaps() {
		return maps;
	}

	public void setMaps(Set<RawMap> maps) {
		this.maps = maps;
	}

	public void addMap(RawMap map) {
		this.maps.add(map);
	}
	
	public void removeMap(RawMap map) {
		this.maps.remove(map);
	}
	
	public List<RawMap> listMaps(){
		return this.maps.stream()
				.sorted()
				.collect(Collectors.toList());
	}
	
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
}
