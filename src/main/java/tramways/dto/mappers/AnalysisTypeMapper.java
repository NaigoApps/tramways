package tramways.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tramways.core.model.analysis.AnalysisType;
import tramways.inbound.model.RoadMapContent;

@Mapper(config = MapperConfiguration.class)
public interface AnalysisTypeMapper {

    @Mapping(target = "id", source = "type.id")
    @Mapping(target = "name", source = "type.name")
    @Mapping(target = "parameters", expression = "java(type.getParameters(roadMap))")
    tramways.inbound.model.AnalysisType map(AnalysisType type, RoadMapContent roadMap);

}
