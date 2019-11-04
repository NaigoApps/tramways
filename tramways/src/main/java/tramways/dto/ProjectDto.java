package tramways.dto;

import java.util.HashSet;
import java.util.Set;

public class ProjectDto extends Dto {

	private String name;

	private UserDto owner;

	private Set<RawMapDto> maps;

	public ProjectDto() {
		maps = new HashSet<>();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Set<RawMapDto> getMaps() {
		return maps;
	}

	public void setMaps(Set<RawMapDto> maps) {
		this.maps = maps;
	}

	public UserDto getOwner() {
		return owner;
	}

	public void setOwner(UserDto owner) {
		this.owner = owner;
	}

}
