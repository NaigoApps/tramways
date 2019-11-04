package tramways.inbound.impl;

import java.util.List;

import javax.inject.Inject;

import tramways.DefaultMessageCollector;
import tramways.dto.RoadMap;
import tramways.inbound.ProjectService;
import tramways.mapper.Json2RoadMapDtoMapper;
import tramways.model.persistable.projects.Project;
import tramways.model.persistable.projects.RoadMapWrapper;
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
		project.setOwner(uService.getSessionData());

		for(RoadMapWrapper map : project.listMaps()) {			
			this.validateMap(map);
		}

		repository.create(project);
	}

	@Override
	public void deleteProject(String uuid) {
		repository.delete(uuid);
	}

	private void validateMap(RoadMapWrapper mapWrapper) {
		RoadMapValidator validator = new RoadMapValidator();
		Json2RoadMapDtoMapper mapper = new Json2RoadMapDtoMapper();
		RoadMap map = mapper.map(mapWrapper.getMap());
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
	
	@Override
	public void editMap(String projectUuid, String mapUuid, String map) {
		Project target = repository.findByUuid(projectUuid);
		RoadMapWrapper oldMap = target.getMap(mapUuid);
		RoadMapWrapper newMap = new RoadMapWrapper();
		newMap.setUuid(oldMap.getUuid());
		newMap.setName(oldMap.getName());
		newMap.setMap(map);
		target.removeMap(oldMap);
		target.addMap(newMap);
		validateMap(target.getMap(mapUuid));
	}
	
	@Override
	public void duplicateMap(String projectUuid, String mapUuid, String mapName) {
		Project target = repository.findByUuid(projectUuid);
		RoadMapWrapper toClone = target.getMap(mapUuid);
		RoadMapWrapper cloned = new RoadMapWrapper();
		cloned.setName(mapName);
		cloned.setMap(toClone.getMap());
		target.addMap(cloned);
	}

	@Override
	public void deleteMap(String projectUuid, String mapUuid) {
		Project target = repository.findByUuid(projectUuid);
		target.removeMap(mapUuid);
	}
}
