package tramways.dto;

public class ProjectDto extends Dto {

	private String name;

	private UserDto owner;

	private String map;
	
	private ProjectStatsDto stats;

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

	public UserDto getOwner() {
		return owner;
	}

	public void setOwner(UserDto owner) {
		this.owner = owner;
	}
	
	public void setStats(ProjectStatsDto stats) {
		this.stats = stats;
	}
	
	public ProjectStatsDto getStats() {
		return stats;
	}

}
