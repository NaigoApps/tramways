package tramways.dto.mappers;

import org.mapstruct.Mapper;

import tramways.core.model.persistable.projects.Project;
import tramways.dto.ProjectDto;

@Mapper(config = MapperConfiguration.class, uses = { UserMapper.class, RawMapMapper.class })
public interface ProjectMapper {

	public abstract ProjectDto map(Project p);

	public abstract Project map(ProjectDto p);

}
