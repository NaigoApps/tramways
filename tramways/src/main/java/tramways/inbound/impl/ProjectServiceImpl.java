package tramways.inbound.impl;

import java.util.List;

import javax.inject.Inject;

import tramways.DefaultMessageCollector;
import tramways.dto.RoadMapDto;
import tramways.inbound.ProjectService;
import tramways.mapper.Json2RoadMapDtoMapper;
import tramways.model.projects.Project;
import tramways.model.projects.RawMap;
import tramways.outbound.ProjectRepository;
import tramways.services.MessageCollector;
import tramways.services.RoadMapValidator;

public class ProjectServiceImpl implements ProjectService {

	@Inject
	private ProjectRepository repository;

	@Inject
	private UserServiceImpl uService;

	@Override
	public List<Project> retrieveProjects(String userUuid) {
		return repository.findByUser(userUuid);
	}

	@Override
	public Project retrieveProject(String uuid) {
		return repository.findByUuid(uuid);
	}

	@Override
	public void createProject(Project project) {
		project.assignUuid();
		project.setOwner(uService.getSessionData());

		project.listMaps().forEach(this::validateMap);

		repository.create(project);
	}

	@Override
	public void deleteProject(String uuid) {
		repository.delete(uuid);
	}

	private void validateMap(RawMap rawMap) {
		RoadMapValidator validator = new RoadMapValidator();
		Json2RoadMapDtoMapper mapper = new Json2RoadMapDtoMapper();
		RoadMapDto map = mapper.map(rawMap.getMap());
		validator.setMap(map);
		MessageCollector collector = new DefaultMessageCollector();
		if (!validator.validate(collector)) {
			throw new IllegalArgumentException("Invalid map: " + collector.listMessages().iterator().next());
		}
	}

	@Override
	public void updateProject(String uuid, String name) {
		repository.update(uuid, name);
	}

}
