package tramways.model.roadmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import tramways.model.Configurable;
import tramways.model.properties.Property;
import tramways.model.roadmap.lanes.LaneSegment;
import tramways.model.roadmap.points.CrossingPoint;
import tramways.model.roadmap.points.DestinationPoint;
import tramways.model.roadmap.points.RelevantPoint;
import tramways.model.roadmap.points.SourcePoint;

public class RoadMap {
	private List<RelevantPoint> points;
	private Map<String, List<Property>> properties;
	
	private List<LaneSegment> lanes;
	private Map<String, Configurable> entitiesMap;
	
	public RoadMap() {
		this.points = new ArrayList<>();
		this.lanes = new ArrayList<>();
		this.properties = new HashMap<>();
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
		entitiesMap = new HashMap<>();
		lanes = new ArrayList<>();
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
							entitiesMap.put(link.getUuid(), link);
						});
						sourceLane.setDestination(point.getUuid());
					});
				}
			}
			this.points.forEach(point -> entitiesMap.put(point.getUuid(), point));
			this.properties.forEach((uuid, props) -> entitiesMap.get(uuid).apply(props));
		}
	}

	private LaneSegment findOrCreateLane(String laneUuid) {
		return LaneSegment.class.cast(entitiesMap.computeIfAbsent(laneUuid, uuid -> {
			LaneSegment segment = new LaneSegment();
			segment.setUuid(uuid);
			lanes.add(segment);
			return segment;
		}));
	}
	
	public RelevantPoint getPoint(String uuid) {
		if(entitiesMap == null) {
			initializeMaps();
		}
		return RelevantPoint.class.cast(entitiesMap.get(uuid));
	}
	
	public <P extends RelevantPoint> P getPoint(String uuid, Class<P> pointClass) {
		if(entitiesMap == null) {
			initializeMaps();
		}
		if(pointClass.isInstance(entitiesMap.get(uuid))) {			
			return pointClass.cast(entitiesMap.get(uuid));
		}
		return null;
	}
	
	public LaneSegment getLane(String uuid) {
		if(entitiesMap == null) {
			initializeMaps();
		}
		return LaneSegment.class.cast(entitiesMap.get(uuid));
	}
	
	public List<Property> getProperties(String uuid){
		return properties.get(uuid);
	}
	
}
