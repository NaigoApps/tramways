package tramways.rs;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import tramways.dto.ConfigurationDto;
import tramways.inbound.ConfigurationService;
import tramways.mapper.ConfigurationMapper;
import tramways.model.Configuration;
import tramways.model.auth.Role;
import tramways.rs.annotations.Roles;

@Path("configurations")
@Transactional
@Roles({ Role.ADMIN, Role.EXPERT })
public class ConfigurationRS {

	@Inject
	private ConfigurationService service;

	@Inject
	private ConfigurationMapper mapper;

	@GET
	public List<ConfigurationDto> listConfigurations() {
		return service.listConfigurations().stream().map(mapper::map).collect(Collectors.toList());
	}

	@POST
	public Long createConfiguration(ConfigurationDto dto) {
		Configuration conf = mapper.map(dto);
		service.createConfiguration(conf);
		return conf.getId();
	}

	@DELETE
	@Path("{confUuid}")
	public void deleteConfiguration(@PathParam("confUuid") String uuid) {
		service.deleteConfiguration(uuid);
	}

}
