package tramways.inbound.model;

import java.util.Objects;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import tramways.inbound.model.AnalysisAllOf;
import tramways.inbound.model.AnalysisResult;
import tramways.inbound.model.AnalysisStatus;
import tramways.inbound.model.Resource;
import javax.validation.constraints.*;
import io.swagger.annotations.*;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2022-03-15T23:51:59.568152500+01:00[Europe/Berlin]")
public class Analysis extends Resource  {
  
  private String name;
  private AnalysisStatus status;
  private AnalysisResult result;

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
  @JsonProperty("status")
  public AnalysisStatus getStatus() {
    return status;
  }
  public void setStatus(AnalysisStatus status) {
    this.status = status;
  }

  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("result")
  public AnalysisResult getResult() {
    return result;
  }
  public void setResult(AnalysisResult result) {
    this.result = result;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Analysis analysis = (Analysis) o;
    return Objects.equals(name, analysis.name) &&
        Objects.equals(status, analysis.status) &&
        Objects.equals(result, analysis.result);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, status, result);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Analysis {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    result: ").append(toIndentedString(result)).append("\n");
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

