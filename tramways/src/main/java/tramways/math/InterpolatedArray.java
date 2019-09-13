package tramways.math;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;

public class InterpolatedArray {
	
	private double minX;
	private double maxX;
	
	private UnivariateFunction function;

	public InterpolatedArray(double[] xs, double[] ys) {
		function = new SplineInterpolator().interpolate(xs, ys);
		minX = xs[0];
		maxX = xs[xs.length - 1];
	}
	
	public double get(double x) {
		return function.value(x);
	}

	public List<Double> get(List<Double> xes) {
		return xes.stream()
				.map(this::get)
				.collect(Collectors.toList());
	}

	public double[] get(double[] xes) {
		return Arrays.stream(xes)
				.map(this::get)
				.toArray();
	}
	
	public double getMinX() {
		return minX;
	}
	
	public double getMaxX() {
		return maxX;
	}
}
