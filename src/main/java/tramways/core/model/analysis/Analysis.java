package tramways.core.model.analysis;

import tramways.core.model.Identifiable;
import tramways.inbound.model.AnalysisResult;

public interface Analysis extends Identifiable {

    AnalysisResult run();

}
