package tramways.dto;

public class RawMapDto extends Dto {

	private String name;

	private String map;

	private MapStatsDto stats;

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

	public MapStatsDto getStats() {
		return stats;
	}

	public void setStats(MapStatsDto stats) {
		this.stats = stats;
	}
}
