package tramways.analysis;

import java.util.Collection;

import tramways.dto.RoadMapDto;

public interface AnalysisProvider {

	Collection<? extends Analysis> availableAnalysis(RoadMapDto roadMap);

}
