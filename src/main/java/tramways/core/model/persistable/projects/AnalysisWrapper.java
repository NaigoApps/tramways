package tramways.core.model.persistable.projects;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import tramways.core.model.analysis.result.AnalysisResult;
import tramways.core.model.persistable.BaseEntity;
import tramways.dto.mappers.AnalysisResultJsonMapper;

@Entity
@Table(name = "analysis")
public class AnalysisWrapper extends BaseEntity {

	private static final AnalysisResultJsonMapper ANALYSIS_RESULT_CONVERTER = new AnalysisResultJsonMapper();

	private String name;

	@Lob
	private String analysisResult;

	public String getAnalysisResult() {
		return analysisResult;
	}

	public void setAnalysisResult(String analysisResult) {
		this.analysisResult = analysisResult;
	}

	public void assignResult(AnalysisResult result) {
		this.analysisResult = ANALYSIS_RESULT_CONVERTER.map(result);
	}

	public AnalysisResult retrieveResult() {
		return ANALYSIS_RESULT_CONVERTER.map(this.analysisResult);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
