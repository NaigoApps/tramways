package tramways.inbound.model;

import java.util.Objects;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import tramways.inbound.model.AnalysisResultType;
import javax.validation.constraints.*;
import io.swagger.annotations.*;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-09-30T22:40:17.034366700+02:00[Europe/Berlin]")@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "resultType", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = XYAnalysisResult.class, name = "XY"),
  @JsonSubTypes.Type(value = StringAnalysisResult.class, name = "STRING"),
})

public class AnalysisResult   {
  
  private AnalysisResultType resultType;

  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("resultType")
  public AnalysisResultType getResultType() {
    return resultType;
  }
  public void setResultType(AnalysisResultType resultType) {
    this.resultType = resultType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AnalysisResult analysisResult = (AnalysisResult) o;
    return Objects.equals(resultType, analysisResult.resultType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(resultType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AnalysisResult {\n");
    
    sb.append("    resultType: ").append(toIndentedString(resultType)).append("\n");
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

