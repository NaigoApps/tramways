package tramways.core.model.persistable.projects;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import tramways.core.model.persistable.BaseEntity;
import tramways.core.model.persistable.users.User;

@Entity
@Table(name = "projects")
public class Project extends BaseEntity {

	private String name;

	@ManyToOne
	private User owner;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<RoadMap> maps;

	public Project() {
		maps = new HashSet<>();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Set<RoadMap> getMaps() {
		return maps;
	}

	public void setMaps(Set<RoadMap> maps) {
		this.maps = maps;
	}

	public void addMap(RoadMap map) {
		this.maps.add(map);
	}

	public RoadMap getMap(String uuid) {
		return this.maps.stream()
				.filter(map -> map.getUuid().equals(uuid))
				.findFirst()
				.orElse(null);
	}

	public void removeMap(RoadMap map) {
		this.maps.remove(map);
	}

	public void removeMap(String mapUuid) {
		maps.removeIf(roadMap -> roadMap.getUuid().equals(mapUuid));
	}

	public List<RoadMap> listMaps() {
		return this.maps.stream().sorted().collect(Collectors.toList());
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
}
