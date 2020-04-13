package tramways.inbound.model;

import java.util.Objects;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import tramways.inbound.model.CrossingLink;
import tramways.inbound.model.Lane;
import tramways.inbound.model.RelevantPoint;
import javax.validation.constraints.*;
import io.swagger.annotations.*;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-04-13T14:51:32.877637400+02:00[Europe/Berlin]")
public class RoadMapAllOf   {
  
  private String name;
  private List<RelevantPoint> points = new ArrayList<RelevantPoint>();
  private List<Lane> lanes = new ArrayList<Lane>();
  private List<CrossingLink> links = new ArrayList<CrossingLink>();

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

  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("links")
  public List<CrossingLink> getLinks() {
    return links;
  }
  public void setLinks(List<CrossingLink> links) {
    this.links = links;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RoadMapAllOf roadMapAllOf = (RoadMapAllOf) o;
    return Objects.equals(name, roadMapAllOf.name) &&
        Objects.equals(points, roadMapAllOf.points) &&
        Objects.equals(lanes, roadMapAllOf.lanes) &&
        Objects.equals(links, roadMapAllOf.links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, points, lanes, links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RoadMapAllOf {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    points: ").append(toIndentedString(points)).append("\n");
    sb.append("    lanes: ").append(toIndentedString(lanes)).append("\n");
    sb.append("    links: ").append(toIndentedString(links)).append("\n");
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

