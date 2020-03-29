package tramways.core.model.analysis.result;

import java.util.List;

public class XYAnalysisResult implements AnalysisResult {

	private String xLabel;
	private String yLabel;

	private List<XYPoint> points;

	public String getXLabel() {
		return xLabel;
	}

	public void setXLabel(String xLabel) {
		this.xLabel = xLabel;
	}

	public String getYLabel() {
		return yLabel;
	}

	public void setYLabel(String yLabel) {
		this.yLabel = yLabel;
	}

	public List<XYPoint> getPoints() {
		return points;
	}

	public void setPoints(List<XYPoint> points) {
		this.points = points;
	}

}
