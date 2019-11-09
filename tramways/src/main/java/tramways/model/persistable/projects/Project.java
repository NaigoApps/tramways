package tramways.model.persistable.projects;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import tramways.model.persistable.BaseEntity;
import tramways.model.persistable.users.User;

@Entity
@Table(name = "projects")
public class Project extends BaseEntity {

	private String name;

	@ManyToOne
	private User owner;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<RoadMapWrapper> maps;

	public Project() {
		maps = new HashSet<>();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Set<RoadMapWrapper> getMaps() {
		return maps;
	}

	public void setMaps(Set<RoadMapWrapper> maps) {
		this.maps = maps;
	}

	public void addMap(RoadMapWrapper map) {
		this.maps.add(map);
	}
	
	public RoadMapWrapper getMap(String uuid) {
		return this.maps.stream()
				.filter(map -> map.getUuid().equals(uuid))
				.findFirst()
				.orElse(null);
	}

	public void removeMap(RoadMapWrapper map) {
		this.maps.remove(map);
	}
	
	public void removeMap(String mapUuid) {
		Iterator<RoadMapWrapper> it = maps.iterator();
		while(it.hasNext()) {
			if(it.next().getUuid().equals(mapUuid)) {
				it.remove();
			}
		}
	}

	public List<RoadMapWrapper> listMaps() {
		return this.maps.stream().sorted().collect(Collectors.toList());
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
}
