package tramways.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tramways.dto.lanes.LaneSegmentDto;
import tramways.dto.points.RelevantPointDto;

public class RoadMapDto {
	private List<RelevantPointDto> points;
	private List<LaneSegmentDto> lanes;
	
	private Map<String, RelevantPointDto> pointsMap;
	private Map<String, LaneSegmentDto> lanesMap;
	
	public void setPoints(List<RelevantPointDto> points) {
		this.points = points;
	}
	
	public void setLanes(List<LaneSegmentDto> lanes) {
		this.lanes = lanes;
	}
	
	public List<RelevantPointDto> getPoints() {
		return points;
	}
	
	public List<LaneSegmentDto> getLanes() {
		return lanes;
	}
	
	private void initializeMaps() {
		pointsMap = new HashMap<>();
		lanesMap = new HashMap<>();
		if(this.points != null) {
			this.points.forEach(point -> pointsMap.put(point.getUuid(), point));
		}
		if(this.lanes != null) {
			this.lanes.forEach(lane -> lanesMap.put(lane.getUuid(), lane));
		}
	}
	
	public RelevantPointDto getPoint(String uuid) {
		if(pointsMap == null) {
			initializeMaps();
		}
		return pointsMap.get(uuid);
	}
	
	public LaneSegmentDto getLane(String uuid) {
		if(pointsMap == null) {
			initializeMaps();
		}
		return lanesMap.get(uuid);
	}
}
