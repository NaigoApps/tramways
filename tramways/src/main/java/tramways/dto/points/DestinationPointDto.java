package tramways.dto.points;

import java.util.List;

public class DestinationPointDto extends RelevantPointDto {

	private List<String> lanes;

	public List<String> getLanes() {
		return lanes;
	}
	
	public void setLanes(List<String> lanes) {
		this.lanes = lanes;
	}

}
