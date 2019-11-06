package tramways.analysis;

import java.math.BigDecimal;

import org.oristool.models.pn.Priority;
import org.oristool.models.stpn.trees.StochasticTransitionFeature;
import org.oristool.petrinet.Marking;
import org.oristool.petrinet.PetriNet;
import org.oristool.petrinet.Place;
import org.oristool.petrinet.Transition;

import tramways.model.roadmap.RoadMap;
import tramways.model.roadmap.points.trafficlight.TrafficLightCrossingPoint;

public class CrossingPointPetriNetMapper {

	private TrafficLightCrossingPoint crossingPoint;
//	private String trafficLight;
//	private String carLine;

	private PetriNet net;
	private Marking marking;

	public void setCrossingPoint(TrafficLightCrossingPoint point) {
		this.crossingPoint = point;
	}

	public PetriNet map(RoadMap map) {

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

		periodTransition.addFeature(StochasticTransitionFeature.newDeterministicInstance(BigDecimal.valueOf(220)));
		delayT.addFeature(
				StochasticTransitionFeature.newUniformInstance(BigDecimal.valueOf(0), BigDecimal.valueOf(120)));
		crosslightAnticipationT.addFeature(StochasticTransitionFeature.newDeterministicInstance(BigDecimal.valueOf(5)));
		crosslightAnticipationT.addFeature(new Priority(0));
		leavingT.addFeature(
				StochasticTransitionFeature.newUniformInstance(BigDecimal.valueOf(6), BigDecimal.valueOf(14)));

		return net;
	}

	public PetriNet getNet() {
		return net;
	}

	public Marking getMarking() {
		return marking;
	}

}
