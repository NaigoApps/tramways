package tramways.inbound.model;

import java.util.Objects;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import tramways.inbound.model.AnalysisResult;
import tramways.inbound.model.AnalysisResultType;
import tramways.inbound.model.XYAnalysisResultAllOf;
import tramways.inbound.model.XYPoint;
import javax.validation.constraints.*;
import io.swagger.annotations.*;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2022-03-15T23:51:59.568152500+01:00[Europe/Berlin]")
public class XYAnalysisResult extends AnalysisResult  {
  
  private String xLabel;
  private String yLabel;
  private List<XYPoint> points = new ArrayList<XYPoint>();

  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("xLabel")
  public String getxLabel() {
    return xLabel;
  }
  public void setxLabel(String xLabel) {
    this.xLabel = xLabel;
  }

  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("yLabel")
  public String getyLabel() {
    return yLabel;
  }
  public void setyLabel(String yLabel) {
    this.yLabel = yLabel;
  }

  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("points")
  public List<XYPoint> getPoints() {
    return points;
  }
  public void setPoints(List<XYPoint> points) {
    this.points = points;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    XYAnalysisResult xyAnalysisResult = (XYAnalysisResult) o;
    return Objects.equals(xLabel, xyAnalysisResult.xLabel) &&
        Objects.equals(yLabel, xyAnalysisResult.yLabel) &&
        Objects.equals(points, xyAnalysisResult.points);
  }

  @Override
  public int hashCode() {
    return Objects.hash(xLabel, yLabel, points);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class XYAnalysisResult {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    xLabel: ").append(toIndentedString(xLabel)).append("\n");
    sb.append("    yLabel: ").append(toIndentedString(yLabel)).append("\n");
    sb.append("    points: ").append(toIndentedString(points)).append("\n");
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

