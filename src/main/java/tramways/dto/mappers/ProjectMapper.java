package tramways.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tramways.core.model.persistable.projects.Project;
import tramways.inbound.model.ProjectDescription;

import java.util.List;

@Mapper(config = MapperConfiguration.class, uses = {UserMapper.class, RoadMapMapper.class})
public interface ProjectMapper {

    @Mapping(target = "roadMaps", source = "maps")
    tramways.inbound.model.Project map(Project p);

    List<tramways.inbound.model.Project> map(List<Project> projects);

    List<ProjectDescription> description(List<Project> projects);
}
