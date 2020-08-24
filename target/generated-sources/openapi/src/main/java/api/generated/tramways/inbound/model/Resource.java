package tramways.inbound.model;

import java.util.Objects;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import io.swagger.annotations.*;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-08-21T17:17:38.852617+02:00[Europe/Rome]")@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "resourceType", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = User.class, name = "User"),
  @JsonSubTypes.Type(value = ProjectDescription.class, name = "ProjectDescription"),
  @JsonSubTypes.Type(value = Project.class, name = "Project"),
  @JsonSubTypes.Type(value = RoadMapDescription.class, name = "RoadMapDescription"),
  @JsonSubTypes.Type(value = RoadMap.class, name = "RoadMap"),
  @JsonSubTypes.Type(value = ItemConfiguration.class, name = "ItemConfiguration"),
  @JsonSubTypes.Type(value = Analysis.class, name = "Analysis"),
  @JsonSubTypes.Type(value = AnalysisDescription.class, name = "AnalysisDescription"),
})

public class Resource   {
  
  private String uuid;
  private String resourceType;

  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("uuid")
  public String getUuid() {
    return uuid;
  }
  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("resourceType")
  public String getResourceType() {
    return resourceType;
  }
  public void setResourceType(String resourceType) {
    this.resourceType = resourceType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Resource resource = (Resource) o;
    return Objects.equals(uuid, resource.uuid) &&
        Objects.equals(resourceType, resource.resourceType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid, resourceType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Resource {\n");
    
    sb.append("    uuid: ").append(toIndentedString(uuid)).append("\n");
    sb.append("    resourceType: ").append(toIndentedString(resourceType)).append("\n");
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

