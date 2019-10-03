package tramways.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import tramways.dto.RoadMapDto;
import tramways.dto.lanes.LaneSegmentDto;
import tramways.dto.points.CrossingPointDto;
import tramways.dto.points.DestinationPointDto;
import tramways.dto.points.LaneSegmentLinkDto;
import tramways.dto.points.SourcePointDto;
import tramways.dto.points.SourcePointType;

public class RoadMapValidator {

	private RoadMapDto map;

	private Map<LaneSegmentDto, SourcePointType> lanesKindMap;

	private MessageCollector collector;

	public boolean validate(MessageCollector collector) {
		this.collector = collector;
		lanesKindMap = new HashMap<>();
		List<SourcePointDto> sources = map.getPoints(SourcePointDto.class);
		for (SourcePointDto source : sources) {
			if (!validateSource(map, source)) {
				return false;
			}
		}
		return true;
	}

	private boolean validateSource(RoadMapDto map, SourcePointDto source) {
		LaneSegmentDto target = map.getLane(source.getTargetLane());
		if (lanesKindMap.get(target) != null) {
			return lanesKindMap.get(target).equals(source.getKind());
		}
		lanesKindMap.put(target, source.getKind());
		return validateLane(map, target);
	}

	private boolean validateLane(RoadMapDto map, LaneSegmentDto lane) {
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
				LaneSegmentDto next = map.getLane(link.getDestination());
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

	public void setMap(RoadMapDto map) {
		this.map = map;
	}
}
