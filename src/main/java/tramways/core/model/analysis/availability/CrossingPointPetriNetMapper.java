package tramways.core.model.analysis.availability;

import java.math.BigDecimal;
import java.util.List;

import org.oristool.models.pn.Priority;
import org.oristool.models.stpn.trees.StochasticTransitionFeature;
import org.oristool.petrinet.Marking;
import org.oristool.petrinet.PetriNet;
import org.oristool.petrinet.Place;
import org.oristool.petrinet.Transition;

import tramways.core.model.analysis.PropertiesCollector;
import tramways.core.model.properties.DefaultPropertySource;
import tramways.core.model.properties.PropertySource;
import tramways.core.model.roadmap.NetworkMap;
import tramways.core.model.roadmap.points.NetworkPoint;
import tramways.inbound.model.Distribution;
import tramways.inbound.model.DistributionProperty;
import tramways.inbound.model.IntegerProperty;
import tramways.inbound.model.Property;
import tramways.math.distributions.DistributionMapper;

import static tramways.core.model.analysis.availability.AvailabilityAnalysisProperties.*;

public class CrossingPointPetriNetMapper {

    private NetworkPoint crossingPoint;

    private PetriNet net;
    private Marking marking;

    public void setCrossingPoint(NetworkPoint point) {
        this.crossingPoint = point;
    }

    public PetriNet map(PropertySource source) {
        net = new PetriNet();
        // Places
        Place incoming = net.addPlace("incoming");
        Place sensor = net.addPlace("sensor");
        Place crossing = net.addPlace("crossing");
        Place red = net.addPlace("red");

        // Transitions
        Transition periodTransition = net.addTransition("period");
        Transition delayT = net.addTransition("delay");
        Transition crosslightAnticipationT = net.addTransition("anticipation");
        Transition leavingT = net.addTransition("leaving");

        // Links
        net.addPostcondition(periodTransition, incoming);
        net.addPrecondition(incoming, delayT);
        net.addPostcondition(delayT, sensor);
        net.addPostcondition(delayT, red);
        net.addPrecondition(sensor, crosslightAnticipationT);
        net.addPostcondition(crosslightAnticipationT, crossing);
        net.addPrecondition(red, leavingT);
        net.addPrecondition(crossing, leavingT);

        // Tokens (here comes the initial marking)
        marking = new Marking();
        marking.setTokens(incoming, 1);
        marking.setTokens(crossing, 0);
        marking.setTokens(sensor, 0);
        marking.setTokens(red, 0);


        Integer period = source.findProperty(PERIOD.name(), IntegerProperty.class).getValue();
        Integer anticipation = source.findProperty(ANTICIPATION.name(), IntegerProperty.class).getValue();
        Distribution delay = source.findProperty(DELAY.name(), DistributionProperty.class).getValue();
        Distribution leaving = source.findProperty(LEAVING.name(), DistributionProperty.class).getValue();

        periodTransition.addFeature(StochasticTransitionFeature.newDeterministicInstance(BigDecimal.valueOf(period)));
        delayT.addFeature(DistributionMapper.map(delay));
        crosslightAnticipationT.addFeature(StochasticTransitionFeature.newDeterministicInstance(
                BigDecimal.valueOf(anticipation))
        );
        crosslightAnticipationT.addFeature(new Priority(0));
        leavingT.addFeature(DistributionMapper.map(leaving));

        return net;
    }

    public PetriNet map(NetworkMap map, List<Property> params, PropertiesCollector collector) {

//		SourcePoint sourcePoint = map.getPoint(target.getSource(), SourcePoint.class);
//		target.getDestination().equals(crossingPoint);
        net = new PetriNet();
        // Places
        Place incoming = net.addPlace("incoming");
        Place sensor = net.addPlace("sensor");
        Place crossing = net.addPlace("crossing");
        Place red = net.addPlace("red");

        // Transitions
        Transition periodTransition = net.addTransition("period");
        Transition delayT = net.addTransition("delay");
        Transition crosslightAnticipationT = net.addTransition("anticipation");
        Transition leavingT = net.addTransition("leaving");

        // Links
        net.addPostcondition(periodTransition, incoming);
        net.addPrecondition(incoming, delayT);
        net.addPostcondition(delayT, sensor);
        net.addPostcondition(delayT, red);
        net.addPrecondition(sensor, crosslightAnticipationT);
        net.addPostcondition(crosslightAnticipationT, crossing);
        net.addPrecondition(red, leavingT);
        net.addPrecondition(crossing, leavingT);

        // Tokens (here comes the initial marking)
        marking = new Marking();
        marking.setTokens(incoming, 1);
        marking.setTokens(crossing, 0);
        marking.setTokens(sensor, 0);
        marking.setTokens(red, 0);


        periodTransition.addFeature(StochasticTransitionFeature.newDeterministicInstance(
                BigDecimal.valueOf(220))
        );
        delayT.addFeature(StochasticTransitionFeature.newUniformInstance(
                BigDecimal.valueOf(0),
                BigDecimal.valueOf(120)
        ));
        crosslightAnticipationT.addFeature(StochasticTransitionFeature.newDeterministicInstance(
                BigDecimal.valueOf(5))
        );
        crosslightAnticipationT.addFeature(new Priority(0));
        leavingT.addFeature(
                StochasticTransitionFeature.newUniformInstance(
                        BigDecimal.valueOf(6),
                        BigDecimal.valueOf(14))
        );

        return net;
    }

    public PetriNet getNet() {
        return net;
    }

    public Marking getMarking() {
        return marking;
    }

}
