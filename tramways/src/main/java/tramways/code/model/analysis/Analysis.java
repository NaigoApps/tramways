package tramways.code.model.analysis;

import tramways.core.model.Identifiable;
import tramways.core.model.analysis.result.AnalysisResult;

public interface Analysis extends Identifiable{

	public AnalysisResult run();
	
}
