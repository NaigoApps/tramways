package tramways.inbound.model;

import java.util.Objects;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import tramways.inbound.model.Property;
import javax.validation.constraints.*;
import io.swagger.annotations.*;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-05-08T22:58:22.447039+02:00[Europe/Berlin]")
public class AnalysisLaunchRequest   {
  
  private String projectId;
  private String mapId;
  private String analysisTypeId;
  private List<Property> parametes = new ArrayList<Property>();

  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("projectId")
  public String getProjectId() {
    return projectId;
  }
  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("mapId")
  public String getMapId() {
    return mapId;
  }
  public void setMapId(String mapId) {
    this.mapId = mapId;
  }

  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("analysisTypeId")
  public String getAnalysisTypeId() {
    return analysisTypeId;
  }
  public void setAnalysisTypeId(String analysisTypeId) {
    this.analysisTypeId = analysisTypeId;
  }

  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("parametes")
  public List<Property> getParametes() {
    return parametes;
  }
  public void setParametes(List<Property> parametes) {
    this.parametes = parametes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AnalysisLaunchRequest analysisLaunchRequest = (AnalysisLaunchRequest) o;
    return Objects.equals(projectId, analysisLaunchRequest.projectId) &&
        Objects.equals(mapId, analysisLaunchRequest.mapId) &&
        Objects.equals(analysisTypeId, analysisLaunchRequest.analysisTypeId) &&
        Objects.equals(parametes, analysisLaunchRequest.parametes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(projectId, mapId, analysisTypeId, parametes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AnalysisLaunchRequest {\n");
    
    sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
    sb.append("    mapId: ").append(toIndentedString(mapId)).append("\n");
    sb.append("    analysisTypeId: ").append(toIndentedString(analysisTypeId)).append("\n");
    sb.append("    parametes: ").append(toIndentedString(parametes)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

