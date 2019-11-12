package tramways.core.model.analysis;

public enum AnalysisResultType {
	XY("xy");

	private String name;

	AnalysisResultType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
