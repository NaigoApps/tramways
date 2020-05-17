package tramways.inbound.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import tramways.core.model.Configurable;
import tramways.core.model.persistable.configurations.Configuration;
import tramways.core.model.roadmap.lanes.LaneSegment;
import tramways.core.model.roadmap.points.LaneSegmentLink;
import tramways.core.model.roadmap.points.NetworkPoint;
import tramways.core.model.roadmap.priority.SensorPriorityManager;
import tramways.core.model.roadmap.priority.TimedPriorityManager;
import tramways.dto.mappers.ConfigurationMapper;
import tramways.inbound.RestUtils;
import tramways.inbound.api.ConfigurationsApiService;
import tramways.inbound.api.NotFoundException;
import tramways.inbound.model.ConfigurableCategory;
import tramways.inbound.model.CreateConfigurationRequest;
import tramways.inbound.model.CrossingLink;
import tramways.inbound.model.ItemConfiguration;
import tramways.inbound.model.Lane;
import tramways.inbound.model.RelevantPoint;
import tramways.inbound.model.UpdateConfigurationRequest;
import tramways.outbound.ConfigurationRepository;

@Transactional
public class ConfigurationApiServiceImpl implements ConfigurationsApiService {

    @Inject
    private ConfigurationRepository repository;

    @Inject
    private ConfigurationMapper mapper;

    @Override
    public Response addConfiguration(String category, CreateConfigurationRequest request, SecurityContext securityContext) throws NotFoundException {
        Configuration conf = new Configuration();
        conf.setName(request.getName());
        conf.setCategory(category);
        conf.setProperties(request.getProps());
        repository.create(conf);
        return RestUtils.ok("Ok");
    }

    @Override
    public Response editConfiguration(String configurationId, UpdateConfigurationRequest request, SecurityContext securityContext) throws NotFoundException {
        Configuration conf = repository.findByUuid(configurationId);
        conf.setName(request.getName());
        conf.setProperties(request.getProps());
        return RestUtils.ok("Ok");
    }

    @Override
    public Response getConfigurationCategories(SecurityContext securityContext) throws NotFoundException {
        return RestUtils.ok(Arrays.asList(
                category(RelevantPoint.class.getSimpleName(), "Network point"),
                category(Lane.class.getSimpleName(), "Lane segment"),
                category(CrossingLink.class.getSimpleName(), "Lane segment link")
        ));
    }

    @Override
    public Response getConfigurations(String category, SecurityContext securityContext) throws NotFoundException {
        return RestUtils.ok(
                repository.findByCategory(category).stream()
                        .map(mapper::map)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public Response removeConfiguration(String configurationId, SecurityContext securityContext) throws NotFoundException {
        repository.delete(configurationId);
        return RestUtils.ok("Ok");
    }

    private ConfigurableCategory category(String name, String description) {
        ConfigurableCategory category = new ConfigurableCategory();
        category.setName(name);
        category.setDescription(description);
        return category;
    }
}
