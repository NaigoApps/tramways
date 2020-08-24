package tramways.inbound.model;

import java.util.Objects;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import tramways.inbound.model.Lane;
import tramways.inbound.model.RelevantPoint;
import javax.validation.constraints.*;
import io.swagger.annotations.*;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-08-21T17:17:38.852617+02:00[Europe/Rome]")
public class RoadMapContent   {
  
  private List<RelevantPoint> points = new ArrayList<RelevantPoint>();
  private List<Lane> lanes = new ArrayList<Lane>();

  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("points")
  public List<RelevantPoint> getPoints() {
    return points;
  }
  public void setPoints(List<RelevantPoint> points) {
    this.points = points;
  }

  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("lanes")
  public List<Lane> getLanes() {
    return lanes;
  }
  public void setLanes(List<Lane> lanes) {
    this.lanes = lanes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RoadMapContent roadMapContent = (RoadMapContent) o;
    return Objects.equals(points, roadMapContent.points) &&
        Objects.equals(lanes, roadMapContent.lanes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(points, lanes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RoadMapContent {\n");
    
    sb.append("    points: ").append(toIndentedString(points)).append("\n");
    sb.append("    lanes: ").append(toIndentedString(lanes)).append("\n");
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

