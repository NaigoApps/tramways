package tramways.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import tramways.model.roadmap.RoadMap;
import tramways.model.roadmap.lanes.LaneSegment;
import tramways.model.roadmap.points.CrossingPoint;
import tramways.model.roadmap.points.DestinationPoint;
import tramways.model.roadmap.points.LaneSegmentLink;
import tramways.model.roadmap.points.RelevantPoint;
import tramways.model.roadmap.points.SourcePoint;
import tramways.model.roadmap.points.SourcePointType;
import tramways.model.roadmap.points.trafficlight.TrafficLightCrossingPoint;

public class RoadMapValidator {

	private RoadMap map;
	
	private Map<String, LaneSegment> lanes;

	private Map<LaneSegment, SourcePointType> lanesKindMap;

	private MessageCollector collector;

	public boolean validate(MessageCollector collector) {
		this.collector = collector;
		lanes = new HashMap<>();
		lanesKindMap = new HashMap<>();
		
		if(map == null) {
			collector.addMessage("Map seems to be empty");
			return false;
		}

		for (RelevantPoint point : map.getPoints()) {
			if (point instanceof SourcePoint) {
				LaneSegment targetLane = findOrCreateLane(((SourcePoint) point).getTargetLane());
				targetLane.setSource(point.getUuid());
			} else if (point instanceof DestinationPoint) {
				((DestinationPoint) point).getLanes().forEach(lane -> {
					LaneSegment targetLane = findOrCreateLane(lane);
					targetLane.setDestination(point.getUuid());
				});
			} else if (point instanceof TrafficLightCrossingPoint) {
				TrafficLightCrossingPoint trafficLightPoint = (TrafficLightCrossingPoint) point;
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

		List<SourcePoint> sources = map.getPoints(SourcePoint.class);
		for (SourcePoint source : sources) {
			if (!validateSource(map, source)) {
				return false;
			}
		}
		return true;
	}

	private LaneSegment findOrCreateLane(String laneUuid) {
		return lanes.computeIfAbsent(laneUuid, uuid -> {
			LaneSegment dto = new LaneSegment();
			dto.setUuid(uuid);
			return dto;
		});
	}

	private boolean validateSource(RoadMap map, SourcePoint source) {
		if(source.getKind() == null) {
			collector.addMessage("Source " + source.getUuid() + " doesn't have a kind");
			return false;			
		}
		
		LaneSegment target = lanes.get(source.getTargetLane());
		
		if(target == null) {
			collector.addMessage("Lane " + source.getTargetLane() + " doesn't exists");
			return false;
		}
		
		if (lanesKindMap.get(target) != null) {
			return lanesKindMap.get(target).equals(source.getKind());
		}
		lanesKindMap.put(target, source.getKind());
		return validateLane(map, target);
	}

	private boolean validateLane(RoadMap map, LaneSegment lane) {
		if (lane.getDestination() == null) {
			collector.addMessage("Lane " + lane.getUuid() + " without a destination");
			return false;
		}
		if (map.getPoint(lane.getDestination(), DestinationPoint.class) != null) {
			return true;
		}
		CrossingPoint crossing = map.getPoint(lane.getDestination(), CrossingPoint.class);
		if (crossing != null) {
			Set<LaneSegmentLink> links = crossing.getConstraints(lane.getUuid());
			for (LaneSegmentLink link : links) {
				LaneSegment next = lanes.get(link.getDestination());
				if (lanesKindMap.get(next) != null) {
					if (lanesKindMap.get(next).equals(lanesKindMap.get(lane))) {
						return true;
					} else {
						collector.addMessage("Lanes " + lane.getUuid() + " and " + next.getUuid() + " are of a different kind");
						return false;
					}
				} else {
					lanesKindMap.put(next, lanesKindMap.get(lane));
					return validateLane(map, next);
				}
			}
		}
		collector.addMessage("Unknown RelevantPoint " + map.getPoint(lane.getDestination()).getUuid());
		return false;
	}

	public void setMap(RoadMap map) {
		this.map = map;
	}
}
