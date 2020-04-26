package tramways.core.model.analysis.availability;

import tramways.core.model.analysis.Analysis;
import tramways.core.model.analysis.AnalysisType;
import tramways.dto.mappers.RoadMapMapper;
import tramways.inbound.model.Property;
import tramways.inbound.model.RelevantPoint;
import tramways.inbound.model.RoadMapContent;
import tramways.services.MessageCollector;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

public class AvailabilityAnalysisType implements AnalysisType {

    @Inject
    private RoadMapMapper mapper;

    @Override
    public String getId() {
        return "availability";
    }

    @Override
    public String getName() {
        return "Availability";
    }

    @Override
    public Analysis createAnalysis(List<Property> params) {
        return new AvailabilityAnalysis();
    }

    @Override
    public boolean isApplicable(RoadMapContent map, List<Property> options, MessageCollector collector) {
        List<RelevantPoint> relevantPoints = map.getPoints();
        //TODO Search for a crossing point
//        if (relevantPoints.isEmpty()) {
//            return collector.addMessage("No crossing points found");
//        }
//        if (relevantPoints.size() > 1) {
//            return collector.addMessage("Multiple crossing points found");
//        }
        return true;
    }

    @Override
    public List<Property> getParameters(RoadMapContent map) {
        return Collections.emptyList();
    }

}
