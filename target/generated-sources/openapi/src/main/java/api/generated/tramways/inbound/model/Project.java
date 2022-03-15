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

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2022-03-15T23:51:59.568152500+01:00[Europe/Berlin]")
public class Project extends Resource  {
  
  private String name;
  private List<RoadMapDescription> roadMaps = new ArrayList<RoadMapDescription>();

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
    Project project = (Project) o;
    return Objects.equals(name, project.name) &&
        Objects.equals(roadMaps, project.roadMaps);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, roadMaps);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Project {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
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

