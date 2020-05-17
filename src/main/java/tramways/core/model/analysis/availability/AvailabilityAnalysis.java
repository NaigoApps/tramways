package tramways.core.model.analysis.availability;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.oristool.analyzer.log.AnalysisMonitor;
import org.oristool.models.stpn.RewardRate;
import org.oristool.models.stpn.TransientSolution;
import org.oristool.models.stpn.trans.RegTransient;
import org.oristool.models.stpn.trees.DeterministicEnablingState;
import org.oristool.petrinet.Marking;
import org.slf4j.LoggerFactory;

import tramways.core.model.AbstractIdentifiable;
import tramways.core.model.analysis.Analysis;
import tramways.core.model.analysis.result.XYAnalysisResult;
import tramways.core.model.analysis.result.XYPoint;

public class AvailabilityAnalysis extends AbstractIdentifiable implements Analysis {

	private static final int FPS = 1;

	private static RegTransient createAnalysis(int period) {
		return RegTransient.builder()
				.greedyPolicy(BigDecimal.valueOf(period), BigDecimal.ZERO)
				.timeBound(BigDecimal.valueOf(period))
				.monitor(new AnalysisMonitor() {

					@Override
					public void notifyMessage(String message) {
						LoggerFactory.getLogger(getClass()).info(message);
					}

					@Override
					public boolean interruptRequested() {
						return false;
					}
				})
				.timeStep(BigDecimal.valueOf(1.0 / FPS))
				.build();
	}

	@Override
	public XYAnalysisResult run() {
		CrossingPointPetriNetMapper mapper = new CrossingPointPetriNetMapper();
//		TrafficLightCrossingPoint point = map.getPoints(TrafficLightCrossingPoint.class).iterator().next();
//		mapper.setCrossingPoint(point);
		mapper.map(null, null, null);

		int analysisTime = 220;
		int steps = analysisTime * FPS + 1;

		double[] time = IntStream.range(0, steps)
				.asDoubleStream()
				.map(v -> v / FPS)
				.toArray();
		double[] av = IntStream.range(0, steps)
				.asDoubleStream()
				.map(v -> 1.0)
				.toArray();

		RegTransient analysis = createAnalysis(220);
		TransientSolution<DeterministicEnablingState, Marking> ssSolution = analysis.compute(mapper.getNet(), mapper.getMarking());

		LoggerFactory.getLogger(getClass()).info("Computing reward...");
		TransientSolution<DeterministicEnablingState, RewardRate> reward = TransientSolution.computeRewards(false,
				ssSolution, "1-red");
		LoggerFactory.getLogger(getClass()).info("...Computed reward");


		for (int tick = 0; tick < steps; tick++) {
			av[tick] *= reward.getSolution()[tick % (220 * FPS)][0][0];
		}

		XYAnalysisResult result = new XYAnalysisResult();
		result.setXLabel("Time");
		result.setYLabel("Availability");

		List<XYPoint> points = new ArrayList<>();
		for(int i = 0;i < time.length;i++) {
			points.add(new XYPoint(time[i], av[i]));
		}
		result.setPoints(points);

		return result;
	}
}
