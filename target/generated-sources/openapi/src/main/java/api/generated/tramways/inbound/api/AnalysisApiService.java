package tramways.inbound.api;

import tramways.inbound.api.*;
import tramways.inbound.model.*;


import tramways.inbound.model.AnalysisLaunchRequest;
import tramways.inbound.model.AnalysisParamsRequest;
import tramways.inbound.model.AnalysisType;
import tramways.inbound.model.Property;
import tramways.inbound.model.StringWrapper;

import java.util.List;
import tramways.inbound.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-04-26T14:37:23.351990800+02:00[Europe/Berlin]")
public interface AnalysisApiService {
      Response getAnalysisParams(AnalysisParamsRequest analysisParamsRequest,SecurityContext securityContext)
      throws NotFoundException;
      Response getAvailableAnalysis(String projectId,String mapId,SecurityContext securityContext)
      throws NotFoundException;
      Response launchAnalysis(AnalysisLaunchRequest analysisLaunchRequest,SecurityContext securityContext)
      throws NotFoundException;
}
