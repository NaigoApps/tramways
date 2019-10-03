package tramways.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import tramways.dto.ProjectDto;
import tramways.dto.ProjectStatsDto;
import tramways.dto.RoadMapDto;
import tramways.dto.points.CrossingPointDto;
import tramways.dto.points.SourcePointDto;
import tramways.model.projects.Project;

@Mapper(config = MapperConfiguration.class, uses = { UserMapper.class })
public interface ProjectMapper {

	@Mapping(target = "stats", ignore = true)
	public abstract ProjectDto map(Project p);

	public abstract Project map(ProjectDto p);

	@AfterMapping
	public default void afterMapping(@MappingTarget ProjectDto dto) {
		Json2RoadMapDtoMapper mapper = new Json2RoadMapDtoMapper();
		RoadMapDto map = mapper.map(dto.getMap());
		ProjectStatsDto stats = new ProjectStatsDto();
		stats.setSegments(map.getLanes().size());
		long crossings = map.getPoints().stream()
				.filter(CrossingPointDto.class::isInstance)
				.count();
		long sources = map.getPoints().stream()
				.filter(SourcePointDto.class::isInstance)
				.count();
		stats.setSegments(map.getLanes().size());
		stats.setCrossings(crossings);
		stats.setSources(sources);
		dto.setStats(stats);
	}

}
