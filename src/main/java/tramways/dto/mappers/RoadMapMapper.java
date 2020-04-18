package tramways.dto.mappers;

import org.mapstruct.Mapper;
import tramways.core.model.persistable.projects.RoadMap;
import tramways.inbound.model.RoadMapDescription;

@Mapper(config = MapperConfiguration.class, uses = {UserMapper.class, AnalysisMapper.class})
public interface RoadMapMapper {

    tramways.inbound.model.RoadMap map(RoadMap m);
    RoadMapDescription description(RoadMap map);
}
