package tramways.inbound.model;

import java.util.Objects;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import io.swagger.annotations.*;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-04-26T14:37:23.351990800+02:00[Europe/Berlin]")
public class AnalysisParamsRequest   {
  
  private String projectId;
  private String mapId;
  private String analysisTypeId;

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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AnalysisParamsRequest analysisParamsRequest = (AnalysisParamsRequest) o;
    return Objects.equals(projectId, analysisParamsRequest.projectId) &&
        Objects.equals(mapId, analysisParamsRequest.mapId) &&
        Objects.equals(analysisTypeId, analysisParamsRequest.analysisTypeId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(projectId, mapId, analysisTypeId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AnalysisParamsRequest {\n");
    
    sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
    sb.append("    mapId: ").append(toIndentedString(mapId)).append("\n");
    sb.append("    analysisTypeId: ").append(toIndentedString(analysisTypeId)).append("\n");
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

