package tramways.model.roadmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import tramways.model.roadmap.lanes.LaneSegment;
import tramways.model.roadmap.points.CrossingPoint;
import tramways.model.roadmap.points.DestinationPoint;
import tramways.model.roadmap.points.RelevantPoint;
import tramways.model.roadmap.points.SourcePoint;

public class RoadMap {
	private List<RelevantPoint> points;
	private List<LaneSegment> lanes;
	
	private Map<String, RelevantPoint> pointsMap;
	private Map<String, LaneSegment> lanesMap;
	
	public RoadMap() {
		this.points = new ArrayList<>();
		this.lanes = new ArrayList<>();
	}
	
	public void setPoints(List<RelevantPoint> points) {
		this.points = points;
	}
	
	public void setLanes(List<LaneSegment> lanes) {
		this.lanes = lanes;
	}
	
	public List<RelevantPoint> getPoints() {
		return points;
	}
	
	public <T extends RelevantPoint> List<T> getPoints(Class<T> pointClass){
		return points.stream()
				.filter(pointClass::isInstance)
				.map(pointClass::cast)
				.collect(Collectors.toList());
	}
	
	public List<LaneSegment> getLanes() {
		return lanes;
	}
	
	public void initialize() {
		initializeMaps();
	}
	
	private void initializeMaps() {
		pointsMap = new HashMap<>();
		lanesMap = new HashMap<>();
		if(this.lanes != null) {
			this.lanes.forEach(lane -> {
				lanesMap.put(lane.getUuid(), lane);
			});
		}
		if(this.points != null) {
			for (RelevantPoint point : points) {
				if (point instanceof SourcePoint) {
					LaneSegment targetLane = findOrCreateLane(((SourcePoint) point).getTargetLane());
					targetLane.setSource(point.getUuid());
				} else if (point instanceof DestinationPoint) {
					((DestinationPoint) point).getLanes().forEach(lane -> {
						LaneSegment targetLane = findOrCreateLane(lane);
						targetLane.setDestination(point.getUuid());
					});
				} else if (point instanceof CrossingPoint) {
					CrossingPoint trafficLightPoint = (CrossingPoint) point;
					trafficLightPoint.getConstraints().forEach((lane, links) -> {
						LaneSegment sourceLane = findOrCreateLane(lane);
						links.forEach(link -> {
							LaneSegment destinationLane = findOrCreateLane(link.getDestination());
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
	}

	private LaneSegment findOrCreateLane(String laneUuid) {
		return lanesMap.computeIfAbsent(laneUuid, uuid -> {
			LaneSegment dto = new LaneSegment();
			dto.setUuid(uuid);
			lanes.add(dto);
			return dto;
		});
	}
	
	public RelevantPoint getPoint(String uuid) {
		if(pointsMap == null) {
			initializeMaps();
		}
		return pointsMap.get(uuid);
	}
	
	public <P extends RelevantPoint> P getPoint(String uuid, Class<P> pointClass) {
		if(pointsMap == null) {
			initializeMaps();
		}
		if(pointClass.isInstance(pointsMap.get(uuid))) {			
			return pointClass.cast(pointsMap.get(uuid));
		}
		return null;
	}
	
	public LaneSegment getLane(String uuid) {
		if(lanesMap == null) {
			initializeMaps();
		}
		return lanesMap.get(uuid);
	}
	
}
