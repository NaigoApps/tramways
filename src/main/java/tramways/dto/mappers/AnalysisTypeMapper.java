package tramways.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tramways.core.model.analysis.AnalysisType;
import tramways.inbound.model.Property;
import tramways.inbound.model.RoadMapContent;

import java.util.List;

@Mapper(config = MapperConfiguration.class)
public interface AnalysisTypeMapper {

    @Mapping(target = "id", source = "type.id")
    @Mapping(target = "name", source = "type.name")
    tramways.inbound.model.AnalysisType map(AnalysisType type, RoadMapContent roadMap, List<Property> properties);

}
