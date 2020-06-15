package tramways.inbound.api;

import tramways.inbound.api.*;
import tramways.inbound.model.*;


import tramways.inbound.model.ConfigurableCategory;
import tramways.inbound.model.CreateConfigurationRequest;
import tramways.inbound.model.ItemConfiguration;
import tramways.inbound.model.Property;
import tramways.inbound.model.StringWrapper;
import tramways.inbound.model.UpdateConfigurationRequest;

import java.util.List;
import tramways.inbound.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-06-08T22:53:33.850861900+02:00[Europe/Berlin]")
public interface ConfigurationsApiService {
      Response addConfiguration(String category,CreateConfigurationRequest createConfigurationRequest,SecurityContext securityContext)
      throws NotFoundException;
      Response editConfiguration(String configurationId,UpdateConfigurationRequest updateConfigurationRequest,SecurityContext securityContext)
      throws NotFoundException;
      Response getConfigurationCategories(SecurityContext securityContext)
      throws NotFoundException;
      Response getConfigurations(String category,SecurityContext securityContext)
      throws NotFoundException;
      Response getPropertiesSuggestions(String category,SecurityContext securityContext)
      throws NotFoundException;
      Response removeConfiguration(String configurationId,SecurityContext securityContext)
      throws NotFoundException;
}
