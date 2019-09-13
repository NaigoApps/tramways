package tramways.dto.points;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CrossingPointDto extends RelevantPointDto {

	private Map<String, Set<LaneSegmentLinkDto>> constraints;

	public CrossingPointDto() {
		constraints = new HashMap<>();
	}

	public Map<String, Set<LaneSegmentLinkDto>> getConstraints() {
		return constraints;
	}
	
	public void setConstraints(Map<String, Set<LaneSegmentLinkDto>> constraints) {
		this.constraints = constraints;
	}
	
}
