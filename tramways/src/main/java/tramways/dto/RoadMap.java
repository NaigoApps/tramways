package tramways.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import tramways.dto.lanes.LaneSegmentDto;
import tramways.dto.points.DestinationPointDto;
import tramways.dto.points.RelevantPointDto;
import tramways.dto.points.SourcePointDto;
import tramways.dto.points.trafficlight.TrafficLightCrossingPointDto;

public class RoadMap {
	private List<RelevantPointDto> points;
	private List<LaneSegmentDto> lanes;
	
	private Map<String, RelevantPointDto> pointsMap;
	private Map<String, LaneSegmentDto> lanesMap;
	
	public RoadMap() {
		this.points = new ArrayList<>();
		this.lanes = new ArrayList<>();
	}
	
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
			for (RelevantPointDto point : points) {
				if (point instanceof SourcePointDto) {
					LaneSegmentDto targetLane = findOrCreateLane(((SourcePointDto) point).getTargetLane());
					targetLane.setSource(point.getUuid());
				} else if (point instanceof DestinationPointDto) {
					((DestinationPointDto) point).getLanes().forEach(lane -> {
						LaneSegmentDto targetLane = findOrCreateLane(lane);
						targetLane.setDestination(point.getUuid());
					});
				} else if (point instanceof TrafficLightCrossingPointDto) {
					TrafficLightCrossingPointDto trafficLightPoint = (TrafficLightCrossingPointDto) point;
					trafficLightPoint.getConstraints().forEach((lane, links) -> {
						LaneSegmentDto sourceLane = findOrCreateLane(lane);
						links.forEach(link -> {
							LaneSegmentDto destinationLane = findOrCreateLane(link.getDestination());
							destinationLane.setSource(point.getUuid());
						});
						sourceLane.setDestination(point.getUuid());
					});
				}
			}
			this.points.forEach(point -> {
				pointsMap.put(point.getUuid(), point);
			});
				
		}
		if(this.lanes != null) {
			this.lanes.forEach(lane -> lanesMap.put(lane.getUuid(), lane));
		}
	}

	private LaneSegmentDto findOrCreateLane(String laneUuid) {
		return lanesMap.computeIfAbsent(laneUuid, uuid -> {
			LaneSegmentDto dto = new LaneSegmentDto();
			dto.setUuid(uuid);
			return dto;
		});
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
	
}
