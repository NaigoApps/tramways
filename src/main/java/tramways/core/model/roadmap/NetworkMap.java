package tramways.core.model.roadmap;

import tramways.core.model.roadmap.points.NetworkPoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
}
