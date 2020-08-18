package tramways.core.model.analysis.availability;

import static tramways.core.model.analysis.availability.AvailabilityAnalysisProperties.ANTICIPATION;
import static tramways.core.model.analysis.availability.AvailabilityAnalysisProperties.DELAY;
import static tramways.core.model.analysis.availability.AvailabilityAnalysisProperties.LEAVING;
import static tramways.core.model.analysis.availability.AvailabilityAnalysisProperties.PERIOD;

import java.math.BigDecimal;
import org.oristool.models.pn.Priority;
import org.oristool.models.stpn.trees.StochasticTransitionFeature;
import org.oristool.petrinet.Marking;
import org.oristool.petrinet.PetriNet;
import org.oristool.petrinet.Place;
import org.oristool.petrinet.Transition;
import tramways.core.model.properties.Properties;
import tramways.core.model.properties.PropertySource;
import tramways.inbound.model.Distribution;
import tramways.inbound.model.DistributionProperty;
import tramways.inbound.model.IntegerProperty;
import tramways.math.distributions.DistributionMapper;

public class CrossingPointPetriNetMapper {

  private PetriNet net;
  private Marking marking;

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
    Transition trafficLightAnticipationT = net.addTransition("anticipation");
    Transition leavingT = net.addTransition("leaving");

    // Links
    net.addPostcondition(periodTransition, incoming);
    net.addPrecondition(incoming, delayT);
    net.addPostcondition(delayT, sensor);
    net.addPostcondition(delayT, red);
    net.addPrecondition(sensor, trafficLightAnticipationT);
    net.addPostcondition(trafficLightAnticipationT, crossing);
    net.addPrecondition(red, leavingT);
    net.addPrecondition(crossing, leavingT);

    // Tokens (here comes the initial marking)
    marking = new Marking();
    marking.setTokens(incoming, 1);
    marking.setTokens(crossing, 0);
    marking.setTokens(sensor, 0);
    marking.setTokens(red, 0);

    Integer period = source.findProperty(PERIOD.name(), IntegerProperty.class).getValue();
    Integer anticipation = source.findProperty(ANTICIPATION.name(), IntegerProperty.class)
        .getValue();
    Distribution delay = Properties.findDistribution(source, DELAY.name());
    Integer fixedDelay = Properties.findInteger(source, DELAY.name());
    Distribution leaving = source.findProperty(LEAVING.name(), DistributionProperty.class)
        .getValue();

    periodTransition.addFeature(
        StochasticTransitionFeature.newDeterministicInstance(BigDecimal.valueOf(period)));
    if (delay != null) {
      delayT.addFeature(DistributionMapper.map(delay));
    } else {
      delayT.addFeature(
          StochasticTransitionFeature.newDeterministicInstance(String.valueOf(fixedDelay)));
    }
    trafficLightAnticipationT.addFeature(
        StochasticTransitionFeature.newDeterministicInstance(BigDecimal.valueOf(anticipation)));
    trafficLightAnticipationT.addFeature(new Priority(0));
    leavingT.addFeature(DistributionMapper.map(leaving));

    return net;
  }

  public PetriNet getNet() {
    return net;
  }

  public Marking getMarking() {
    return marking;
  }

}
