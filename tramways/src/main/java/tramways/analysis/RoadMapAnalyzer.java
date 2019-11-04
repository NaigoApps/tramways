package tramways.analysis;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import tramways.dto.RoadMap;

/**
 *
 * @author naigo
 */
public class RoadMapAnalyzer {

	private RoadMap roadMap;
	
	@Inject
	private Instance<AnalysisProvider> analyzers;
	
	public List<Analysis> listAvailableAnalysis(){
		List<Analysis> result = new ArrayList<>();
		analyzers.forEach(analyzer -> result.addAll(analyzer.availableAnalysis(roadMap)));
		return result;
	}
//	private static final int FPS = 1;
//	
//	private TramwaySystem system;
//
//	private int analysisTime;
//
//	private Set<TransientSolution<DeterministicEnablingState, Marking>> solution;
//
//	public void setSystem(TramwaySystem system) {
//		this.system = system;
//		solution = new HashSet<>();
//	}
//
//	public void setAnalysisTime(int analysisTime) {
//		this.analysisTime = analysisTime;
//	}
//
//	public InterpolatedArray analyzeAvailability(CarControlPoint point) {
//		int steps = analysisTime * FPS + 1;
//		double[] time = IntStream.range(0, steps)
//				.asDoubleStream()
//				.map(v -> v / FPS)
//				.toArray();
//		double[] av = IntStream.range(0, steps)
//				.asDoubleStream()
//				.map(v -> 1.0)
//				.toArray();
//		
//		if (solution.isEmpty()) {
//			Set<TramwaySystem> subsystems = system.getSubsystems();
//			int i = 1;
//			for(TramwaySystem subsystem : system.getSubsystems()) {				
//				LoggerFactory.getLogger(getClass()).info("Analyzing {} of {}...", i++, subsystems.size());
//				TramwaySubsystemMapper mapper = new TramwaySubsystemMapper();
//				mapper.setSystem(subsystem);
//				mapper.map();
//				RegTransient analysis = createAnalysis(subsystem.getPeriod());
//				TransientSolution<DeterministicEnablingState, Marking> ssSolution = analysis.compute(mapper.getNet(), mapper.getMarking());
//				
//				LoggerFactory.getLogger(getClass()).info("Computing reward...");
//				TransientSolution<DeterministicEnablingState, RewardRate> reward = TransientSolution.computeRewards(false,
//						ssSolution, mapper.getGreen(point));
//				LoggerFactory.getLogger(getClass()).info("...Computed reward");
//
//				
//				for (int tick = 0; tick < steps; tick++) {
//					av[tick] *= reward.getSolution()[tick % (subsystem.getPeriod() * FPS)][0][0];
//				}
//				LoggerFactory.getLogger(getClass()).info("...Analyzed");
//			}
//		}
//
//
//		return new InterpolatedArray(time, av);
//	}
//
//	private static RegTransient createAnalysis(int period) {
//		return RegTransient.builder()
//				.greedyPolicy(BigDecimal.valueOf(period), BigDecimal.ZERO)
//				.timeBound(BigDecimal.valueOf(period))
//				.monitor(new AnalysisMonitor() {
//
//					@Override
//					public void notifyMessage(String message) {
//						LoggerFactory.getLogger(getClass()).info(message);
//					}
//
//					@Override
//					public boolean interruptRequested() {
//						return false;
//					}
//				})
//				.timeStep(BigDecimal.valueOf(1.0 / FPS))
//				.build();
//	}
//	
//	public InterpolatedArray analyzeOverflowProbability(CarControlPoint point) {
//		InterpolatedArray availability = analyzeAvailability(point);
//		
//		Queue q = new Queue(ExponentialDistribution.of(point.getArrivalRate()),
//				ExponentialDistribution.of(1.0 / point.getTraversingTime()), point.getQueueCapacity());
//		CarQueueAnalyzer analyzer = new CarQueueAnalyzer(q);
//
//		analyzer.setAvailability(availability);
//
//		InterpolatedArray[] results = analyzer.computeByQueueSize(analysisTime);
//
//		double[] probability = new double[analysisTime + 1];
//		for (int i = 0; i < probability.length; i++) {
//			probability[i] = results[results.length - 1].get(i);
//		}
//
//		double[] xes = IntStream.range(0, probability.length).asDoubleStream().toArray();
//
//		return new InterpolatedArray(xes, probability);
//	}
//	
//	public InterpolatedArray[] analyzeBySize(CarControlPoint point) {
//		InterpolatedArray availability = analyzeAvailability(point);
//		
//		Queue q = new Queue(ExponentialDistribution.of(point.getArrivalRate()),
//				ExponentialDistribution.of(1.0 / point.getTraversingTime()), point.getQueueCapacity());
//		CarQueueAnalyzer analyzer = new CarQueueAnalyzer(q);
//
//		analyzer.setAvailability(availability);
//
//		return analyzer.computeByQueueSize(analysisTime);
//	}
//	
//	public double[][] analyzeByTime(CarControlPoint point) {
//		InterpolatedArray availability = analyzeAvailability(point);
//		
//		Queue q = new Queue(ExponentialDistribution.of(point.getArrivalRate()),
//				ExponentialDistribution.of(1.0 / point.getTraversingTime()), point.getQueueCapacity());
//		CarQueueAnalyzer analyzer = new CarQueueAnalyzer(q);
//
//		analyzer.setAvailability(availability);
//
//		return analyzer.computeByTime(analysisTime);
//	}
//	
//	public InterpolatedArray analyzeMeanQueueSize(CarControlPoint point) {
//		InterpolatedArray availability = analyzeAvailability(point);
//		
//		Queue q = new Queue(ExponentialDistribution.of(point.getArrivalRate()),
//				ExponentialDistribution.of(1.0 / point.getTraversingTime()), point.getQueueCapacity());
//		CarQueueAnalyzer analyzer = new CarQueueAnalyzer(q);
//
//		analyzer.setAvailability(availability);
//
//		double[][] results = analyzer.computeByTime(analysisTime);
//
//		double[] tes = IntStream.range(0, analysisTime + 1).asDoubleStream().toArray();
//		double[] avg = new double[analysisTime + 1];
//		
//		for (int i = 0; i < avg.length; i++) {
//			for (int c = 0;c < results[i].length;c++) {
//				avg[i] += c * results[i][c];
//			}
//		}
//
//		return new InterpolatedArray(tes, avg);
//	}

}
