package tramways.dto.mappers;

import javax.inject.Inject;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import tramways.dto.MapStatsDto;
import tramways.dto.RawMapDto;
import tramways.model.persistable.projects.Project;
import tramways.model.persistable.projects.RoadMapWrapper;
import tramways.model.roadmap.RoadMap;
import tramways.model.roadmap.points.CrossingPoint;
import tramways.model.roadmap.points.SourcePoint;
import tramways.outbound.ProjectRepository;

@Mapper(config = MapperConfiguration.class, uses = { UserMapper.class, AnalysisMapper.class })
public abstract class RawMapMapper {

	@Inject
	private ProjectRepository projectsRepo;

	@Mapping(target = "stats", ignore = true)
	public abstract RawMapDto map(RoadMapWrapper m);

	@Mapping(target = "id", ignore = true)
	public abstract RoadMapWrapper map(RawMapDto m);

	protected Project find(String projectId) {
		if (projectId != null) {
			return projectsRepo.findByUuid(projectId);
		}
		return null;
	}

	@AfterMapping
	protected void afterMapping(@MappingTarget RawMapDto dto) {
		Json2RoadMapDtoMapper mapper = new Json2RoadMapDtoMapper();
		RoadMap map = mapper.map(dto.getMap());
		MapStatsDto stats = new MapStatsDto();
		stats.setSegments(map.getLanes().size());
		long crossings = map.getPoints().stream().filter(CrossingPoint.class::isInstance).count();
		long sources = map.getPoints().stream().filter(SourcePoint.class::isInstance).count();
		stats.setSegments(map.getLanes().size());
		stats.setCrossings(crossings);
		stats.setSources(sources);
		dto.setStats(stats);
	}

}
