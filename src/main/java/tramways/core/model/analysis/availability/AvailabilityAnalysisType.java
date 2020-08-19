package tramways.core.model.analysis.availability;

import static tramways.core.model.analysis.availability.AvailabilityAnalysisProperties.ANTICIPATION;
import static tramways.core.model.analysis.availability.AvailabilityAnalysisProperties.CROSSING_POINT;
import static tramways.core.model.analysis.availability.AvailabilityAnalysisProperties.DELAY;
import static tramways.core.model.analysis.availability.AvailabilityAnalysisProperties.LANE_TYPE;
import static tramways.core.model.analysis.availability.AvailabilityAnalysisProperties.LEAVING;
import static tramways.core.model.analysis.availability.AvailabilityAnalysisProperties.PERIOD;
import static tramways.core.model.analysis.availability.AvailabilityAnalysisProperties.TRAM_DESTINATION;
import static tramways.core.model.analysis.availability.AvailabilityAnalysisProperties.TRAM_SOURCE;
import static tramways.core.model.roadmap.RoadMapUtilities.findNetworkPoint;
import static tramways.core.model.roadmap.RoadMapUtilities.findTramDestinations;
import static tramways.core.model.roadmap.RoadMapUtilities.findTramSources;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import tramways.core.model.analysis.Analysis;
import tramways.core.model.analysis.BasicAnalysisType;
import tramways.core.model.analysis.PropertiesCollector;
import tramways.core.model.properties.CompoundPropertySource;
import tramways.core.model.properties.ConfigurablePropertySource;
import tramways.core.model.properties.DefaultPropertySource;
import tramways.core.model.properties.Properties;
import tramways.core.model.properties.PropertySource;
import tramways.core.model.properties.builder.PropertyBuilder;
import tramways.core.model.roadmap.NetworkMap;
import tramways.core.model.roadmap.RoadMapNetworkMapper;
import tramways.core.model.roadmap.lanes.LaneSegment;
import tramways.core.model.roadmap.points.LaneSegmentLink;
import tramways.core.model.roadmap.points.NetworkPoint;
import tramways.inbound.model.ChoiceElement;
import tramways.inbound.model.ChoiceProperty;
import tramways.inbound.model.CrossingLink;
import tramways.inbound.model.DistributionProperty;
import tramways.inbound.model.IntegerProperty;
import tramways.inbound.model.Lane;
import tramways.inbound.model.Property;
import tramways.inbound.model.RelevantPoint;
import tramways.inbound.model.RoadMapContent;
import tramways.services.MessagesCollector;

public class AvailabilityAnalysisType extends BasicAnalysisType {

    @Override
    public String getId() {
        return "availability";
    }

    @Override
    public String getName() {
        return "Availability";
    }

    @Override
    public Analysis createAnalysis(NetworkMap networkMap, List<Property> params) {
        CompoundPropertySource propertySource = new CompoundPropertySource();
        propertySource.addSources(
            networkMap.listPoints().stream().map(ConfigurablePropertySource::new)
                .collect(Collectors.toList()));
        propertySource.addSources(
            networkMap.listLanes().stream().map(ConfigurablePropertySource::new)
                .collect(Collectors.toList()));
        propertySource.addSources(
            networkMap.listLinks().stream().map(ConfigurablePropertySource::new)
                .collect(Collectors.toList()));
        propertySource.addSource(new DefaultPropertySource(params));
        return new AvailabilityAnalysis(propertySource);
    }

    private List<NetworkPoint> findCrossingPoints(List<NetworkPoint> networkPoints) {
        return networkPoints.stream()
            .filter(point -> !point.getSources().isEmpty())
            .filter(point -> !point.getDestinations().isEmpty())
            .collect(Collectors.toList());
    }

    @Override
    public void prepareAnalysis(RoadMapContent map, List<Property> parameters,
        PropertiesCollector propertiesCollector,
        MessagesCollector messagesCollector) {
        super.prepareAnalysis(map, parameters, propertiesCollector, messagesCollector);

        PropertySource propertySource = new DefaultPropertySource(parameters);
        RoadMapNetworkMapper networkMapper = new RoadMapNetworkMapper(map);
        NetworkMap networkMap = networkMapper.map();

        ChoiceProperty crossingPointProperty = propertySource
            .findProperty(CROSSING_POINT.name(), ChoiceProperty.class);
        if (crossingPointProperty == null) {
            propertiesCollector.collectProperty(createCrossingPointProperty(networkMap));
            return;
        }
        NetworkPoint crossingPoint = networkMap.findPoint(crossingPointProperty.getValue());
        if (crossingPoint == null) {
            crossingPointProperty.setValid(false);
            propertiesCollector.collectProperty(crossingPointProperty);
            return;
        }

        ChoiceProperty sourcePointProperty = propertySource
            .findProperty(TRAM_SOURCE.name(), ChoiceProperty.class);
        if (sourcePointProperty == null) {
            propertiesCollector.collectProperty(createSourcePointProperty(networkMap));
            return;
        }
        NetworkPoint sourcePoint = networkMap.findPoint(sourcePointProperty.getValue());
        if (sourcePoint == null) {
            sourcePointProperty.setValid(false);
            propertiesCollector.collectProperty(sourcePointProperty);
            return;
        }

        ChoiceProperty destinationPointProperty = propertySource
            .findProperty(TRAM_DESTINATION.name(), ChoiceProperty.class);
        if (destinationPointProperty == null) {
            propertiesCollector.collectProperty(createDestinationPointProperty(networkMap));
            return;
        }
        NetworkPoint destinationPoint = networkMap.findPoint(destinationPointProperty.getValue());
        if (destinationPoint == null) {
            destinationPointProperty.setValid(false);
            propertiesCollector.collectProperty(destinationPointProperty);
            return;
        }

        LaneSegment tramLaneA = sourcePoint.getDestinations().get(0);
        LaneSegment tramLaneB = destinationPoint.getSources().get(0);
        LaneSegmentLink tramLink =
            crossingPoint.getLinks(tramLaneA).stream()
                .filter(link -> tramLaneB.equals(link.getDestination())).findFirst().orElse(null);

        if (tramLink == null) {
            messagesCollector.addMessage(
                tramLaneA.getUuid() + " and " + tramLaneB.getUuid() + " are not linked");
            return;
        }

        IntegerProperty periodProperty = sourcePoint
            .getProperty(PERIOD.name(), IntegerProperty.class);
        Property distributionDelayProperty = tramLaneA
            .getProperty(DELAY.name(), DistributionProperty.class);
        Property integerDelayProperty = tramLaneA.getProperty(DELAY.name(), IntegerProperty.class);
        Property anticipationProperty = tramLink
            .getProperty(ANTICIPATION.name(), IntegerProperty.class);
        Property leavingProperty = tramLink.getProperty(LEAVING.name(), DistributionProperty.class);

        if (periodProperty == null) {
            periodProperty = propertySource.findProperty(PERIOD.name(), IntegerProperty.class);
            if (periodProperty == null) {
                propertiesCollector.collectProperty(Properties.intProperty(PERIOD.name(), 0));
            }
        }
        if (distributionDelayProperty == null && integerDelayProperty == null) {
            distributionDelayProperty = propertySource
                .findProperty(DELAY.name(), DistributionProperty.class);
            integerDelayProperty = propertySource.findProperty(DELAY.name(), IntegerProperty.class);
            if (distributionDelayProperty == null && integerDelayProperty == null) {
                propertiesCollector
                    .collectProperty(Properties.uniformDistributionProperty(DELAY.name()));
            }
        }
        if (anticipationProperty == null) {
            anticipationProperty = propertySource
                .findProperty(ANTICIPATION.name(), IntegerProperty.class);
            if (anticipationProperty == null) {
                propertiesCollector.collectProperty(Properties.intProperty(ANTICIPATION.name(), 0));
            }
        }
        if (leavingProperty == null) {
            leavingProperty = propertySource
                .findProperty(LEAVING.name(), DistributionProperty.class);
            if (leavingProperty == null) {
                propertiesCollector
                    .collectProperty(Properties.uniformDistributionProperty(LEAVING.name()));
            }
        }
    }

    private Predicate<LaneSegment> isTram() {
        return laneSegment -> LaneType.TRAM.name()
            .equals(Properties.findString(laneSegment.listProperties(), LANE_TYPE));
    }

    private Property createCrossingPointProperty(NetworkMap networkMap) {
        List<NetworkPoint> points = findCrossingPoints(networkMap.listPoints());
        return PropertyBuilder.choice(CROSSING_POINT, "Crossing point")
            .options(points, point -> Properties.choiceElement(point.getUuid(), point.getUuid()))
            .build();
    }

    private Property createSourcePointProperty(NetworkMap networkMap) {
        List<NetworkPoint> points = findTramSources(networkMap.listPoints());
        return PropertyBuilder.choice(TRAM_SOURCE, "Tram source")
            .options(points, point -> Properties.choiceElement(point.getUuid(), point.getUuid()))
            .build();
    }

    private Property createDestinationPointProperty(NetworkMap networkMap) {
        List<NetworkPoint> points = findTramDestinations(networkMap.listPoints());
        return PropertyBuilder.choice(TRAM_DESTINATION, "Tram destination")
            .options(points, point -> Properties.choiceElement(point.getUuid(), point.getUuid()))
            .build();
    }

    private NetworkPoint resolveTramSource(NetworkMap map, PropertySource propertySource,
        PropertiesCollector propertiesCollector,
        MessagesCollector messagesCollector) {
        List<NetworkPoint> tramSources = findTramSources(map.listPoints());
        if (tramSources.isEmpty()) {
            messagesCollector.addMessage("Tram source point not found");
            return null;
        }

        NetworkPoint tramSource = null;
        if (tramSources.size() > 1) {
            ChoiceProperty tramSourceProperty = propertySource
                .findProperty(TRAM_SOURCE.name(), ChoiceProperty.class);
            if (tramSourceProperty != null) {
                String tramSourceName = tramSourceProperty.getValue();
                tramSource = findNetworkPoint(map, tramSourceName);
            }
        } else {
            tramSource = tramSources.get(0);
        }

        if (tramSource == null) {
            ChoiceProperty property = Properties
                .choiceProperty(TRAM_SOURCE.name(), null, tramSources.stream()
                    .map(networkPoint -> Properties.choiceElement(
                        networkPoint.getUuid(),
                        networkPoint.getUuid()))
                    .toArray(ChoiceElement[]::new));
            propertiesCollector.collectProperty(property);
        }

        return tramSource;
    }

    private NetworkPoint resolveTramDestination(NetworkMap map, PropertySource propertySource,
        PropertiesCollector propertiesCollector, MessagesCollector messagesCollector) {
        List<NetworkPoint> tramDestinations = findTramDestinations(map.listPoints());
        if (tramDestinations.isEmpty()) {
            messagesCollector.addMessage("Tram destination point not found");
            return null;
        }

        NetworkPoint tramDestination = null;
        if (tramDestinations.size() > 1) {
            ChoiceProperty tramDestinationProperty = propertySource
                .findProperty(TRAM_DESTINATION.name(), ChoiceProperty.class);
            if (tramDestinationProperty != null) {
                String tramDestinationName = tramDestinationProperty.getValue();
                tramDestination = findNetworkPoint(map, tramDestinationName);
            }
        } else {
            tramDestination = tramDestinations.get(0);
        }

        if (tramDestination == null) {
            ChoiceProperty property = Properties
                .choiceProperty(TRAM_DESTINATION.name(), null, tramDestinations.stream()
                    .map(
                        networkPoint -> Properties.choiceElement(
                            networkPoint.getUuid(),
                            networkPoint.getUuid()))
                    .toArray(ChoiceElement[]::new));
            propertiesCollector.collectProperty(property);
        }

        return tramDestination;
    }

    private NetworkPoint resolveCrossingPoint(NetworkMap map, PropertySource propertySource,
        PropertiesCollector propertiesCollector,
        MessagesCollector messagesCollector) {
        List<NetworkPoint> crossings = findCrossingPoints(map.listPoints());
        if (crossings.isEmpty()) {
            messagesCollector.addMessage("Crossing point not found");
            return null;
        }

        NetworkPoint crossingPoint = null;
        if (crossings.size() > 1) {
            ChoiceProperty crossingsProperty = propertySource
                .findProperty(CROSSING_POINT.name(), ChoiceProperty.class);
            if (crossingsProperty != null) {
                String crossingPointName = crossingsProperty.getValue();
                crossingPoint = findNetworkPoint(map, crossingPointName);
            }
        } else {
            crossingPoint = crossings.get(0);
        }

        if (crossingPoint == null) {
            ChoiceProperty property = new ChoiceProperty();
            property.setName(CROSSING_POINT.name());
            property.setDescription(CROSSING_POINT.getDescription());
            property.setChoices(crossings.stream()
                .map(networkPoint -> Properties
                    .choiceElement(networkPoint.getUuid(), networkPoint.getUuid()))
                .collect(Collectors.toList()));
            propertiesCollector.collectProperty(property);
        }

        return crossingPoint;
    }

    @Override
    public List<Property> prepareAnalysis(String category) {
        List<Property> result = new ArrayList<>();
        if (category == null || RelevantPoint.class.getSimpleName().equals(category)) {
            result.add(Properties.intProperty(PERIOD.name(), 0));
        }
        if (category == null || Lane.class.getSimpleName().equals(category)) {
            result.add(Properties.choiceProperty(LANE_TYPE.name(), null,
                Properties.choiceElement(LaneType.CAR.name(), LaneType.CAR.getDescription()),
                Properties.choiceElement(LaneType.TRAM.name(), LaneType.TRAM.getDescription())));
            result.add(Properties.uniformDistributionProperty(DELAY.name()));
        }
        if (category == null || CrossingLink.class.getSimpleName().equals(category)) {
            result.add(Properties.intProperty(ANTICIPATION.name(), 0));
            result.add(Properties.uniformDistributionProperty(LEAVING.name()));
        }
        return result;
    }

}
