package tramways.dto;

import java.util.ArrayList;
import java.util.List;

public class RawMapDto extends Dto {

	private String name;

	private String map;

	private MapStatsDto stats;

	private List<AnalysisDto> analysis;

	public RawMapDto() {
		analysis = new ArrayList<>();
	}

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

	public List<AnalysisDto> getAnalysis() {
		return analysis;
	}

	public void setAnalysis(List<AnalysisDto> analysis) {
		this.analysis = analysis;
	}
}
