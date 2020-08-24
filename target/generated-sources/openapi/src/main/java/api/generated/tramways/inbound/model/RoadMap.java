package tramways.inbound.model;

import java.util.Objects;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import tramways.inbound.model.AnalysisDescription;
import tramways.inbound.model.Resource;
import tramways.inbound.model.RoadMapAllOf;
import tramways.inbound.model.RoadMapContent;
import javax.validation.constraints.*;
import io.swagger.annotations.*;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-08-21T17:17:38.852617+02:00[Europe/Rome]")
public class RoadMap extends Resource  {
  
  private String name;
  private RoadMapContent content;
  private List<AnalysisDescription> analysis = new ArrayList<AnalysisDescription>();

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
  @JsonProperty("content")
  public RoadMapContent getContent() {
    return content;
  }
  public void setContent(RoadMapContent content) {
    this.content = content;
  }

  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("analysis")
  public List<AnalysisDescription> getAnalysis() {
    return analysis;
  }
  public void setAnalysis(List<AnalysisDescription> analysis) {
    this.analysis = analysis;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RoadMap roadMap = (RoadMap) o;
    return Objects.equals(name, roadMap.name) &&
        Objects.equals(content, roadMap.content) &&
        Objects.equals(analysis, roadMap.analysis);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, content, analysis);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RoadMap {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    analysis: ").append(toIndentedString(analysis)).append("\n");
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

