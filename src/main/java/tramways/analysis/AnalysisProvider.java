package tramways.analysis;

import java.util.Collection;

import tramways.core.model.roadmap.NetworkMap;

public interface AnalysisProvider {

	Collection<? extends Analysis> availableAnalysis(NetworkMap networkMap);

}
