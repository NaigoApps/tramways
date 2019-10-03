package tramways.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	
	public <T extends RelevantPointDto> List<T> getPoints(Class<T> pointClass){
		return points.stream()
				.filter(pointClass::isInstance)
				.map(pointClass::cast)
				.collect(Collectors.toList());
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
	
	public <P extends RelevantPointDto> P getPoint(String uuid, Class<P> pointClass) {
		if(pointsMap == null) {
			initializeMaps();
		}
		if(pointClass.isInstance(pointsMap.get(uuid))) {			
			return pointClass.cast(pointsMap.get(uuid));
		}
		return null;
	}
	
	public LaneSegmentDto getLane(String uuid) {
		if(lanesMap == null) {
			initializeMaps();
		}
		return lanesMap.get(uuid);
	}
	
	public <L extends LaneSegmentDto> L getLane(String uuid, Class<L> laneClass) {
		if(lanesMap == null) {
			initializeMaps();
		}
		if(laneClass.isInstance(lanesMap.get(uuid))) {			
			return laneClass.cast(lanesMap.get(uuid));
		}
		return null;
	}
}
