package tramways.inbound.impl;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import org.slf4j.LoggerFactory;
import tramways.DefaultMessagesCollector;
import tramways.core.model.analysis.Analysis;
import tramways.core.model.analysis.AnalysisType;
import tramways.core.model.analysis.AnalysisTypeFactory;
import tramways.core.model.analysis.DefaultAnalysisProperties;
import tramways.core.model.analysis.DefaultPropertiesCollector;
import tramways.core.model.analysis.PropertiesCollector;
import tramways.core.model.persistable.projects.Project;
import tramways.core.model.persistable.projects.RoadMap;
import tramways.core.model.properties.Properties;
import tramways.core.model.roadmap.NetworkMap;
import tramways.core.model.roadmap.RoadMapNetworkMapper;
import tramways.dto.mappers.AnalysisTypeMapper;
import tramways.inbound.RestUtils;
import tramways.inbound.api.AnalysisApiService;
import tramways.inbound.model.AnalysisRequest;
import tramways.inbound.model.AnalysisResponse;
import tramways.inbound.model.AnalysisResultType;
import tramways.inbound.model.AnalysisStatus;
import tramways.inbound.model.RoadMapContent;
import tramways.inbound.model.StringAnalysisResult;
import tramways.outbound.ProjectRepository;
import tramways.services.MessagesCollector;

@Transactional
public class AnalysisApiServiceImpl implements AnalysisApiService {

    @Inject
    Event<AnalysisEvent> analysisEvent;

    @Inject
    private AnalysisTypeFactory factory;

    @Inject
    private AnalysisTypeMapper mapper;

    @Inject
    private ProjectRepository projectRepository;

    @Inject
    private AnalysisResultSaver saver;

    @Override
    public Response getAvailableAnalysis(String projectId, String mapId,
        SecurityContext securityContext) {
        Project project = projectRepository.findById(projectId);
        RoadMap map = project.getMap(mapId);
        RoadMapContent mapContent = map.getContent();
        return RestUtils.ok(
            factory.getTypes().stream()
                .map(type -> mapper.map(type, mapContent, Collections.emptyList()))
                .collect(Collectors.toList()));
    }

    @Override
    public Response launchAnalysis(AnalysisRequest request, SecurityContext securityContext) {
        AnalysisType analysisType = factory.getType(request.getAnalysisTypeId());
        Project project = projectRepository.findById(request.getProjectId());
        RoadMap map = project.getMap(request.getMapId());
        NetworkMap networkMap = new RoadMapNetworkMapper(map.getContent()).map();

        MessagesCollector messagesCollector = new DefaultMessagesCollector();
        PropertiesCollector propertiesCollector = new DefaultPropertiesCollector();

        analysisType.prepareAnalysis(map.getContent(), request.getParameters(), propertiesCollector,
            messagesCollector);
        if (propertiesCollector.listProperties().isEmpty() && messagesCollector.listMessages()
            .isEmpty()) {
            Analysis analysis = analysisType.createAnalysis(networkMap, request.getParameters());

            tramways.core.model.persistable.projects.Analysis persistable = new tramways.core.model.persistable.projects.Analysis();
            persistable.setName(Properties.findString(request.getParameters(),
                DefaultAnalysisProperties.ANALYSIS_NAME.name()));
            persistable.setStatus(AnalysisStatus.IN_PROGRESS);
            map.addAnalysis(persistable);
            analysisEvent.fire(
                new AnalysisEvent(request.getProjectId(), request.getMapId(), analysis,
                    persistable.getUuid()));
        }

        return createResponse(messagesCollector, propertiesCollector);
    }

    private Response createResponse(MessagesCollector messagesCollector,
        PropertiesCollector propertiesCollector) {
        AnalysisResponse response = new AnalysisResponse();
        response.setWarnings(messagesCollector.listMessages());
        response.setParameters(propertiesCollector.listProperties());
        response.setOk(
            messagesCollector.listMessages().isEmpty() && propertiesCollector.listProperties()
                .isEmpty());

        return RestUtils.ok(response);
    }

    public void runAnalysis(
        @Observes(during = TransactionPhase.AFTER_COMPLETION) AnalysisEvent event) {
        CompletableFuture.supplyAsync(event.getAnalysis()::run).handle((result, error) -> {
            if (error == null) {
                saver.persistAnalysisResult(event.getProjectId(), event.getMapId(),
                    event.getPersistableUuid(), result, true);
            } else {
                LoggerFactory.getLogger(getClass()).error("Error", error);
                StringAnalysisResult errorResult = new StringAnalysisResult();
                errorResult.setResultType(AnalysisResultType.STRING);
                errorResult.setMessage(error.getMessage());
                saver.persistAnalysisResult(event.getProjectId(), event.getMapId(),
                    event.getPersistableUuid(), errorResult, false);
            }
            return null;
        });
    }

    private class AnalysisEvent {

        private final String projectId;
        private final String mapId;
        private final Analysis analysis;
        private final String persistableUuid;

        public AnalysisEvent(String projectId, String mapId, Analysis analysis,
            String persistableUuid) {
            this.projectId = projectId;
            this.mapId = mapId;
            this.analysis = analysis;
            this.persistableUuid = persistableUuid;
        }

        public String getProjectId() {
            return projectId;
        }

        public String getMapId() {
            return mapId;
        }

        public Analysis getAnalysis() {
            return analysis;
        }

        public String getPersistableUuid() {
            return persistableUuid;
        }
    }
}
