package tramways.model.roadmap.points;

import tramways.dto.ConfigurableDto;

/*
 * Example properties: weight of the link, crossingTime, ...
 */
public class LaneSegmentLink extends ConfigurableDto {

	private String destination;

	public LaneSegmentLink() {
		//Nothing to do
	}
	
	public LaneSegmentLink(String destination) {
		this.destination = destination;
	}

	public String getDestination() {
		return destination;
	}
	
	public void setDestination(String destination) {
		this.destination = destination;
	}

}
