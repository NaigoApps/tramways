package tramways.inbound.model;

import java.util.Objects;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import tramways.inbound.model.Property;
import javax.validation.constraints.*;
import io.swagger.annotations.*;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-08-21T17:17:38.852617+02:00[Europe/Rome]")
public class AnalysisResponse   {
  
  private List<String> warnings = new ArrayList<String>();
  private List<Property> parameters = new ArrayList<Property>();
  private Boolean ok;

  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("warnings")
  public List<String> getWarnings() {
    return warnings;
  }
  public void setWarnings(List<String> warnings) {
    this.warnings = warnings;
  }

  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("parameters")
  public List<Property> getParameters() {
    return parameters;
  }
  public void setParameters(List<Property> parameters) {
    this.parameters = parameters;
  }

  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("ok")
  public Boolean getOk() {
    return ok;
  }
  public void setOk(Boolean ok) {
    this.ok = ok;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AnalysisResponse analysisResponse = (AnalysisResponse) o;
    return Objects.equals(warnings, analysisResponse.warnings) &&
        Objects.equals(parameters, analysisResponse.parameters) &&
        Objects.equals(ok, analysisResponse.ok);
  }

  @Override
  public int hashCode() {
    return Objects.hash(warnings, parameters, ok);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AnalysisResponse {\n");
    
    sb.append("    warnings: ").append(toIndentedString(warnings)).append("\n");
    sb.append("    parameters: ").append(toIndentedString(parameters)).append("\n");
    sb.append("    ok: ").append(toIndentedString(ok)).append("\n");
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

