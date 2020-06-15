package tramways.core.model.roadmap;

import tramways.core.model.roadmap.lanes.LaneSegment;
import tramways.core.model.roadmap.points.LaneSegmentLink;
import tramways.core.model.roadmap.points.NetworkPoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class NetworkMap {
    private final List<NetworkPoint> points;

    public NetworkMap() {
        this.points = new ArrayList<>();
    }

    public List<NetworkPoint> listPoints() {
        return new ArrayList<>(points);
    }

    public void addPoints(Collection<NetworkPoint> points) {
        this.points.addAll(points);
    }

    public void addPoints(NetworkPoint... points) {
        this.points.addAll(Arrays.asList(points));
    }

    public Collection<LaneSegment> listLanes() {
        return points.stream()
                .flatMap(networkPoint -> networkPoint.getLinks().keySet().stream())
                .collect(Collectors.toSet());
    }

    public Collection<LaneSegmentLink> listLinks() {
        return points.stream()
                .flatMap(networkPoint -> networkPoint.getLinks().values().stream())
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }
}
