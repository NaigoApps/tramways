package tramways.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tramways.core.model.persistable.projects.Analysis;
import tramways.inbound.model.AnalysisDescription;

@Mapper(config = MapperConfiguration.class, uses = {})
public interface AnalysisMapper {

    @Mapping(target = "resourceType", constant = "Analysis")
    tramways.inbound.model.Analysis map(Analysis a);

    @Mapping(target = "resourceType", constant = "AnalysisDescription")
    AnalysisDescription description(Analysis analysis);

}
