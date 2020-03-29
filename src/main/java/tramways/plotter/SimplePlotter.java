package tramways.plotter;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.Range;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

public class SimplePlotter extends ApplicationFrame {

	private static final long serialVersionUID = 1L;
	private XYSeries series;
	private JFreeChart chart;
	private List<Double> xs;
	private List<Double> ys;

	public SimplePlotter(String title, String seriesName) {
		super(title);
		this.series = new XYSeries(seriesName);
		final XYSeriesCollection data = new XYSeriesCollection(series);
		chart = ChartFactory.createXYLineChart(title, "X", "Y", data, PlotOrientation.VERTICAL, true, true, false);
		chart.getXYPlot().setDataset(data);

		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(1000, 600));
		setContentPane(chartPanel);
	}

	public void setXs(List<Double> xes) {
		this.xs = xes;
	}

	public void setYs(List<Double> yes) {
		this.ys = yes;
		updateUI();
	}

	public void setYBounds(double min, double max) {
		Range r = new Range(min, max);
		chart.getXYPlot().getRangeAxis().setRangeWithMargins(r, true, true);
	}
	
	private void updateUI() {
		series.clear();
		for (int i = 0; i < xs.size(); i++) {
			series.addOrUpdate(xs.get(i), ys.get(i));
		}
		chart.fireChartChanged();
	}
}
