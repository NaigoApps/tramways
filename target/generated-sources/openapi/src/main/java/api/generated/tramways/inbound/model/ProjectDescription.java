package tramways.inbound.model;

import java.util.Objects;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import tramways.inbound.model.ProjectDescriptionAllOf;
import tramways.inbound.model.Resource;
import tramways.inbound.model.RoadMapDescription;
import javax.validation.constraints.*;
import io.swagger.annotations.*;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-04-13T17:27:03.760042100+02:00[Europe/Berlin]")
public class ProjectDescription   {
  
  private String uuid;
  private String name;
  private List<RoadMapDescription> roadMaps = new ArrayList<RoadMapDescription>();

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
  @JsonProperty("name")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("roadMaps")
  public List<RoadMapDescription> getRoadMaps() {
    return roadMaps;
  }
  public void setRoadMaps(List<RoadMapDescription> roadMaps) {
    this.roadMaps = roadMaps;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProjectDescription projectDescription = (ProjectDescription) o;
    return Objects.equals(uuid, projectDescription.uuid) &&
        Objects.equals(name, projectDescription.name) &&
        Objects.equals(roadMaps, projectDescription.roadMaps);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid, name, roadMaps);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectDescription {\n");
    
    sb.append("    uuid: ").append(toIndentedString(uuid)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    roadMaps: ").append(toIndentedString(roadMaps)).append("\n");
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

