package tramways.inbound.impl;

import org.slf4j.LoggerFactory;
import tramways.DefaultMessagesCollector;
import tramways.core.model.analysis.Analysis;
import tramways.core.model.analysis.AnalysisType;
import tramways.core.model.analysis.AnalysisTypeFactory;
import tramways.core.model.analysis.DefaultPropertiesCollector;
import tramways.core.model.analysis.PropertiesCollector;
import tramways.core.model.analysis.result.AnalysisResult;
import tramways.core.model.persistable.projects.Project;
import tramways.core.model.persistable.projects.RoadMap;
import tramways.core.model.roadmap.NetworkMap;
import tramways.core.model.roadmap.RoadMapNetworkMapper;
import tramways.dto.mappers.AnalysisTypeMapper;
import tramways.inbound.RestUtils;
import tramways.inbound.api.AnalysisApiService;
import tramways.inbound.api.NotFoundException;
import tramways.inbound.model.AnalysisRequest;
import tramways.inbound.model.AnalysisResponse;
import tramways.inbound.model.RoadMapContent;
import tramways.outbound.ProjectRepository;
import tramways.services.MessagesCollector;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Transactional
public class AnalysisApiServiceImpl implements AnalysisApiService {

    @Inject
    private AnalysisTypeFactory factory;

    @Inject
    private AnalysisTypeMapper mapper;

    @Inject
    private ProjectRepository projectRepository;

    @Override
    public Response getAvailableAnalysis(String projectId, String mapId, SecurityContext securityContext) throws NotFoundException {
        Project project = projectRepository.findById(projectId);
        RoadMap map = project.getMap(mapId);
        RoadMapContent mapContent = map.getContent();
        return RestUtils.ok(factory.getTypes().stream()
                .map(type -> mapper.map(type, mapContent, Collections.emptyList()))
                .collect(Collectors.toList()));
    }

    @Override
    public Response launchAnalysis(AnalysisRequest request, SecurityContext securityContext) throws NotFoundException {
        AnalysisType analysisType = factory.getType(request.getAnalysisTypeId());

        Project project = projectRepository.findById(request.getProjectId());
        RoadMap map = project.getMap(request.getMapId());
        NetworkMap networkMap = new RoadMapNetworkMapper(map.getContent()).map();
        Analysis analysis = analysisType.createAnalysis(networkMap, request.getParameters());

        CompletableFuture<AnalysisResult> future = CompletableFuture
                .supplyAsync(analysis::run)
                .exceptionally((throwable -> {
                    LoggerFactory.getLogger(getClass()).error("Error", throwable);
                    return null;
                }));
        future.thenAccept(result -> persistAnalysisResult(request.getProjectId(), request.getMapId(), "TEST", result));
        return RestUtils.ok(null);
    }

    @Override
    public Response prepareAnalysis(AnalysisRequest request, SecurityContext securityContext) throws NotFoundException {
        Project project = projectRepository.findById(request.getProjectId());
        RoadMap map = project.getMap(request.getMapId());

        MessagesCollector messagesCollector = new DefaultMessagesCollector();
        PropertiesCollector propertiesCollector = new DefaultPropertiesCollector();
        factory.getType(request.getAnalysisTypeId()).prepareAnalysis(map.getContent(), request.getParameters(), propertiesCollector, messagesCollector);

        AnalysisResponse response = new AnalysisResponse();
        response.setWarnings(messagesCollector.listMessages());
        response.setParameters(propertiesCollector.listProperties());
        return RestUtils.ok(response);
    }

    private void persistAnalysisResult(String projectId, String mapId, String analysisName, AnalysisResult result){
        if(result == null){
            return;
        }
        Project project = projectRepository.findById(projectId);
        RoadMap map = project.getMap(mapId);
        tramways.core.model.persistable.projects.Analysis persistable = new tramways.core.model.persistable.projects.Analysis();
        persistable.setName(analysisName);
        persistable.assignResult(result);
        map.addAnalysis(persistable);
    }
}
