package tramways.core.model.persistable.projects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;
import tramways.core.model.persistable.BaseEntity;
import tramways.dto.mappers.AnalysisResultJsonMapper;
import tramways.inbound.model.AnalysisResult;
import tramways.inbound.model.AnalysisStatus;

@Entity
@Table(name = "analysis")
public class Analysis extends BaseEntity {

    private static final AnalysisResultJsonMapper ANALYSIS_RESULT_CONVERTER = new AnalysisResultJsonMapper();

    @Enumerated(EnumType.STRING)
    private AnalysisStatus status;

    private String name;

    @Lob
    private String analysisResult;

    public void setResult(AnalysisResult result) {
        this.analysisResult = ANALYSIS_RESULT_CONVERTER.map(result);
    }

    public AnalysisResult getResult() {
        return ANALYSIS_RESULT_CONVERTER.map(this.analysisResult);
    }

    public void setStatus(AnalysisStatus status) {
        this.status = status;
    }

    public AnalysisStatus getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
