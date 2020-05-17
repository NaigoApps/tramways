package tramways.core.model.analysis.availability;

import tramways.core.model.Configurable;
import tramways.core.model.analysis.Analysis;
import tramways.core.model.analysis.AnalysisType;
import tramways.core.model.roadmap.NetworkMap;
import tramways.core.model.roadmap.RoadMapNetworkMapper;
import tramways.core.model.roadmap.points.NetworkPoint;
import tramways.inbound.model.ChoiceProperty;
import tramways.inbound.model.Property;
import tramways.inbound.model.RoadMapContent;
import tramways.services.MessageCollector;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import static tramways.core.model.properties.Properties.choiceElement;
import static tramways.core.model.properties.Properties.choiceProperty;

public class AvailabilityAnalysisType implements AnalysisType {

    @Override
    public String getId() {
        return "availability";
    }

    @Override
    public String getName() {
        return "Availability";
    }

    @Override
    public Analysis createAnalysis(List<Property> params) {
        return new AvailabilityAnalysis();
    }

    @Override
    public void collectWarnings(RoadMapContent map, List<Property> options, MessageCollector collector) {
        RoadMapNetworkMapper networkMapper = new RoadMapNetworkMapper(map);
        NetworkMap networkMap = networkMapper.map();

        List<NetworkPoint> networkPoints = networkMap.listPoints();
        if (networkPoints.size() != 5) {
            collector.addMessage("This analysis requires exactly 5 nodes");
        }
        NetworkPoint tramSource = findTramSource(networkPoints);
        if (tramSource == null) {
            collector.addMessage("Tram source point not found");
        }
        NetworkPoint carSource = findCarSource(networkPoints);
        if (carSource == null) {
            collector.addMessage("Car source point not found");
        }
        NetworkPoint tramDestination = findTramDestination(networkPoints);
        if (tramDestination == null) {
            collector.addMessage("Tram destination non found");
        }
        NetworkPoint carDestination = findCarDestination(networkPoints);
        if (carDestination == null) {
            collector.addMessage("Car destination not found");
        }
        NetworkPoint crossingPoint = findCrossingPoint(networkPoints);
        if (crossingPoint == null) {
            collector.addMessage("Crossing point not found");
        }
        if (carSource != null && tramSource != null &&
                crossingPoint != null &&
                carDestination != null && tramDestination != null) {
            checkLinks(collector, tramSource, carSource, tramDestination, carDestination, crossingPoint);
        }
    }

    private void checkLinks(MessageCollector collector, NetworkPoint tramSource, NetworkPoint carSource, NetworkPoint tramDestination, NetworkPoint carDestination, NetworkPoint crossingPoint) {
        if (!linked(carSource, crossingPoint)) {
            collector.addMessage("Car source not linked to crossing point");
        }
        if (!linked(tramSource, crossingPoint)) {
            collector.addMessage("Tram source not linked to crossing point");
        }
        if (!linked(crossingPoint, tramDestination)) {
            collector.addMessage("Crossing point not linked to tram destination");
        }
        if (!linked(crossingPoint, carDestination)) {
            collector.addMessage("Crossing point not linked to car destination");
        }
    }

    private boolean linked(NetworkPoint s, NetworkPoint d) {
        return s.getDestinations().stream()
                .anyMatch(laneSegment -> d.equals(laneSegment.getDestination()));
    }

    private NetworkPoint findCrossingPoint(List<NetworkPoint> networkPoints) {
        return networkPoints.stream()
                .filter(point -> !point.getSources().isEmpty())
                .filter(point -> !point.getDestinations().isEmpty())
                .findFirst()
                .orElse(null);
    }

    private NetworkPoint findTramSource(List<NetworkPoint> points) {
        return findSource(points, "tram");
    }

    private NetworkPoint findCarSource(List<NetworkPoint> points) {
        return findSource(points, "car");
    }

    private NetworkPoint findTramDestination(List<NetworkPoint> points) {
        return findDestination(points, "tram");
    }

    private NetworkPoint findCarDestination(List<NetworkPoint> points) {
        return findDestination(points, "car");
    }

    private NetworkPoint findSource(List<NetworkPoint> points, String laneType) {
        return points.stream()
                .filter(point -> point.getDestinations().size() == 1)
                .filter(point -> this.hasChoiceProperty("laneType", laneType).test(point.getDestinations().iterator().next()))
                .findFirst()
                .orElse(null);
    }

    private NetworkPoint findDestination(List<NetworkPoint> points, String laneType) {
        return points.stream()
                .filter(point -> point.getSources().size() == 1)
                .filter(point -> this.hasChoiceProperty("laneType", laneType).test(point.getSources().iterator().next()))
                .findFirst()
                .orElse(null);
    }

    private Predicate<Configurable> hasChoiceProperty(String name, String value) {
        return configurable -> configurable.listProperties().stream()
                .filter(property -> property.getName().equals(name))
                .filter(property -> property instanceof ChoiceProperty)
                .map(ChoiceProperty.class::cast)
                .anyMatch(choiceProperty -> choiceProperty.getValue().equals(value));
    }

    @Override
    public List<Property> getParameters(RoadMapContent map) {
        return Collections.singletonList(
                choiceProperty("Test choice", "A",
                        choiceElement("A", "CAR"),
                        choiceElement("B", "TRAM")
                )
        );
    }

}
