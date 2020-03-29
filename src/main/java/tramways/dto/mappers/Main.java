/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tramways.dto.mappers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import javax.swing.SwingUtilities;

import tramways.plotter.SimplePlotter;

/**
 *
 * @author naigo
 */
public class Main {

	public static void main(String[] args) {
		
		
		
//		TramSourcePoint tramSource = new TramSourcePoint();
//		TramLineSegment tramLineA = new TramLineSegment();
//		tramSource.setTargetLine(tramLineA);
//		TramLineSegment tramLineB = new TramLineSegment();
//		DestinationPoint tramDestination = new DestinationPoint();
//		tramDestination.addLine(tramLineB);
//		
//		SourcePoint carSource = new SourcePoint();
//		CarLineSegment carLineA = new CarLineSegment();
//		carSource.setTargetLine(carLineA);
//		CarLineSegment carLineB = new CarLineSegment();
//		DestinationPoint carDestination = new DestinationPoint();
//		carDestination.addLine(carLineB);
//		
//		CarTramCrossingPoint crossingPoint = new  CarTramCrossingPoint();
//		crossingPoint.setTramSource(tramLineA);
//		crossingPoint.setTramDestination(tramLineB);
//		crossingPoint.setBlockedSource(carLineA);
//		crossingPoint.setBlockedDestination(carLineB);
		
		
//		
//		
//		
//
//		ModelAnalyzer analyzer = new ModelAnalyzer();
//
//		analyzer.setSystem(system);
//
//		/* T = [0, analysisSeconds] */
//		int analysisSeconds = system.getPeriod() * 2;
//
//		CarControlPoint controlPoint1 = new CarControlPoint();
//		controlPoint1.addCrossing(system.getCrossingPoint("TCP1_1"));
//		controlPoint1.setArrivalRate(0.2);
//		controlPoint1.setTraversingTime(4);
//		controlPoint1.setQueueCapacity(100);
//
//		CarControlPoint controlPoint2 = new CarControlPoint();
//		controlPoint2.addCrossing(system.getCrossingPoint("TCP1_2"));
//		
//		CarControlPoint controlPoint3 = new CarControlPoint();
//		controlPoint3.addCrossing(system.getCrossingPoint("TCP1_3"));
//		
//		
//		analyzer.setAnalysisTime(analysisSeconds);
//
//		InterpolatedArray availability1 = analyzer.analyzeAvailability(controlPoint1);
//		InterpolatedArray availability2 = analyzer.analyzeAvailability(controlPoint2);
//		InterpolatedArray availability3 = analyzer.analyzeAvailability(controlPoint3);
////		InterpolatedArray meanQueueSize = analyzer.analyzeMeanQueueSize(controlPoint);
////		double[][] queueSize = analyzer.analyzeByTime(controlPoint);
////		controlPoint.setQueueCapacity(controlPoint.getQueueCapacity() + 1);
////		InterpolatedArray overflowProbability = analyzer.analyzeOverflowProbability(controlPoint);
//
//		showGraph(analysisSeconds, availability1);
//		showGraph(analysisSeconds, availability2);
//		showGraph(analysisSeconds, availability3);
////		showGraph(analysisSeconds, meanQueueSize);
////		showGraph(analysisSeconds, overflowProbability);
////		showGraphs(queueSize, 1);
//	}
//
//	private static void showGraph(int xMax, InterpolatedArray function) {
//		List<Double> xes = IntStream.range(0, xMax).asDoubleStream().boxed().collect(Collectors.toList());
//
//		List<Double> yes1 = function.get(xes);
//
//		SimplePlotter plotter1 = new SimplePlotter("test", "test");
//		plotter1.setXs(xes);
//		plotter1.setYs(yes1);
//		plotter1.pack();
//		plotter1.setVisible(true);
	}

	private static void showGraphs(double[][] functions, double maxY) {
		List<Double> xes = IntStream.range(0, functions[0].length).asDoubleStream().boxed()
				.collect(Collectors.toList());

		SimplePlotter plotter1 = new SimplePlotter("test", "test");
		plotter1.setXs(xes);
		plotter1.setYs(xes);
		plotter1.setYBounds(0, maxY);
		plotter1.pack();
		plotter1.setVisible(true);

		new Thread(() -> {
			for (double[] array : functions) {
				SwingUtilities.invokeLater(() -> {
					plotter1.setYs(DoubleStream.of(array).boxed().collect(Collectors.toList()));
				});
				try {
					Thread.sleep(17);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}).start();
	}

}
