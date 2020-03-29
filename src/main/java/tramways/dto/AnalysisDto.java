package tramways.dto;

public class AnalysisDto extends Dto {
	private String name;
	private String analysisResult;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setAnalysisResult(String analysisResult) {
		this.analysisResult = analysisResult;
	}

	public String getAnalysisResult() {
		return analysisResult;
	}
}
