package tramways.model.analysis;

import tramways.model.Identifiable;
import tramways.model.analysis.result.AnalysisResult;

public interface Analysis extends Identifiable{

	public AnalysisResult run();
	
}
