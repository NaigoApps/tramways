package tramways.dto.mappers;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import tramways.DefaultMessageCollector;
import tramways.core.model.analysis.AnalysisType;
import tramways.inbound.model.Property;
import tramways.inbound.model.RoadMapContent;
import tramways.services.MessageCollector;

import java.util.List;

@Mapper(config = MapperConfiguration.class)
public abstract class AnalysisTypeMapper {

    @Mapping(target = "id", source = "type.id")
    @Mapping(target = "name", source = "type.name")
    @Mapping(target = "warning", ignore = true)
    @Mapping(target = "parameters", ignore = true)
    public abstract tramways.inbound.model.AnalysisType map(AnalysisType type, RoadMapContent roadMap, List<Property> properties);

    @AfterMapping
    protected void afterMapping(@MappingTarget tramways.inbound.model.AnalysisType target, AnalysisType type, RoadMapContent roadMap, List<Property> properties){
        target.setParameters(type.getParameters(roadMap));
        MessageCollector collector = new DefaultMessageCollector();
        type.collectWarnings(roadMap, properties, collector);
        if (!collector.listMessages().isEmpty()) {
            target.setWarning(collector.listMessages().iterator().next());
        }
    }

}
