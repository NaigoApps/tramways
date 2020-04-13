package tramways.dto.mappers;

import org.mapstruct.Mapper;
import tramways.core.model.persistable.projects.RoadMap;
import tramways.inbound.model.RoadMapDescription;

@Mapper(config = MapperConfiguration.class, uses = {UserMapper.class, AnalysisMapper.class})
public interface RoadMapMapper {

    default tramways.inbound.model.RoadMap map(RoadMap m) {
        return m.getContent();
    }

    RoadMapDescription description(RoadMap map);
}
