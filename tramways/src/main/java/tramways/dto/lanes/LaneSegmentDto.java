package tramways.dto.lanes;

import tramways.dto.ConfigurableDto;

public class LaneSegmentDto extends ConfigurableDto {
	
	private String source;
	private String destination;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

}
