package tramways.analysis;

import java.util.Collection;

import tramways.core.model.roadmap.RoadMap;

public interface AnalysisProvider {

	Collection<? extends Analysis> availableAnalysis(RoadMap roadMap);

}
