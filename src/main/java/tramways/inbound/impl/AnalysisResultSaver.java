package tramways.inbound.impl;

import javax.inject.Inject;
import javax.transaction.Transactional;
import tramways.core.model.persistable.projects.Analysis;
import tramways.inbound.model.AnalysisResult;
import tramways.inbound.model.AnalysisStatus;
import tramways.outbound.ProjectRepository;

@Transactional
public class AnalysisResultSaver {

    @Inject
    private ProjectRepository projectRepository;

    public void persistAnalysisResult(String projectId, String mapId, String analysisId,
        AnalysisResult result, boolean ok) {
        Analysis persistable = projectRepository.findById(projectId).getMap(mapId)
            .getAnalysis(analysisId);
        persistable.setResult(result);
        if (ok) {
            persistable.setStatus(AnalysisStatus.DONE);
        } else {
            persistable.setStatus(AnalysisStatus.ERROR);
        }
    }

}
