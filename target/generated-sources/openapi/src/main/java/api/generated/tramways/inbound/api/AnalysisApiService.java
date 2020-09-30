package tramways.inbound.api;

import tramways.inbound.api.*;
import tramways.inbound.model.*;


import tramways.inbound.model.AnalysisRequest;
import tramways.inbound.model.AnalysisResponse;
import tramways.inbound.model.AnalysisType;

import java.util.List;
import tramways.inbound.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-09-30T22:40:17.034366700+02:00[Europe/Berlin]")
public interface AnalysisApiService {
      Response getAvailableAnalysis(String projectId,String mapId,SecurityContext securityContext)
      throws NotFoundException;
      Response launchAnalysis(AnalysisRequest analysisRequest,SecurityContext securityContext)
      throws NotFoundException;
}
