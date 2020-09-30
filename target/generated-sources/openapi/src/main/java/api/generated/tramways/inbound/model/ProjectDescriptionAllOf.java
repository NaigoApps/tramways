package tramways.inbound.model;

import java.util.Objects;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import tramways.inbound.model.RoadMapDescription;
import javax.validation.constraints.*;
import io.swagger.annotations.*;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-09-30T22:40:17.034366700+02:00[Europe/Berlin]")
public class ProjectDescriptionAllOf   {
  
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
    ProjectDescriptionAllOf projectDescriptionAllOf = (ProjectDescriptionAllOf) o;
    return Objects.equals(name, projectDescriptionAllOf.name) &&
        Objects.equals(roadMaps, projectDescriptionAllOf.roadMaps);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, roadMaps);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProjectDescriptionAllOf {\n");
    
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

