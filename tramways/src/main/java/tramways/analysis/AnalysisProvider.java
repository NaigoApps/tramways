package tramways.analysis;

import java.util.Collection;

import tramways.dto.RoadMap;

public interface AnalysisProvider {

	Collection<? extends Analysis> availableAnalysis(RoadMap roadMap);

}
