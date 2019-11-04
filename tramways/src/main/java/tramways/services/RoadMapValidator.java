package tramways.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import tramways.dto.RoadMap;
import tramways.dto.lanes.LaneSegmentDto;
import tramways.dto.points.CrossingPointDto;
import tramways.dto.points.DestinationPointDto;
import tramways.dto.points.LaneSegmentLinkDto;
import tramways.dto.points.RelevantPointDto;
import tramways.dto.points.SourcePointDto;
import tramways.dto.points.SourcePointType;
import tramways.dto.points.trafficlight.TrafficLightCrossingPointDto;

public class RoadMapValidator {

	private RoadMap map;
	
	private Map<String, LaneSegmentDto> lanes;

	private Map<LaneSegmentDto, SourcePointType> lanesKindMap;

	private MessageCollector collector;

	public boolean validate(MessageCollector collector) {
		this.collector = collector;
		lanes = new HashMap<>();
		lanesKindMap = new HashMap<>();
		
		if(map == null) {
			collector.addMessage("Map seems to be empty");
			return false;
		}

		for (RelevantPointDto point : map.getPoints()) {
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

		List<SourcePointDto> sources = map.getPoints(SourcePointDto.class);
		for (SourcePointDto source : sources) {
			if (!validateSource(map, source)) {
				return false;
			}
		}
		return true;
	}

	private LaneSegmentDto findOrCreateLane(String laneUuid) {
		return lanes.computeIfAbsent(laneUuid, uuid -> {
			LaneSegmentDto dto = new LaneSegmentDto();
			dto.setUuid(uuid);
			return dto;
		});
	}

	private boolean validateSource(RoadMap map, SourcePointDto source) {
		if(source.getKind() == null) {
			collector.addMessage("Source " + source.getUuid() + " doesn't have a kind");
			return false;			
		}
		
		LaneSegmentDto target = lanes.get(source.getTargetLane());
		
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

	private boolean validateLane(RoadMap map, LaneSegmentDto lane) {
		if (lane.getDestination() == null) {
			collector.addMessage("Lane " + lane.getUuid() + " without a destination");
			return false;
		}
		if (map.getPoint(lane.getDestination(), DestinationPointDto.class) != null) {
			return true;
		}
		CrossingPointDto crossing = map.getPoint(lane.getDestination(), CrossingPointDto.class);
		if (crossing != null) {
			Set<LaneSegmentLinkDto> links = crossing.getConstraints(lane.getUuid());
			for (LaneSegmentLinkDto link : links) {
				LaneSegmentDto next = lanes.get(link.getDestination());
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
