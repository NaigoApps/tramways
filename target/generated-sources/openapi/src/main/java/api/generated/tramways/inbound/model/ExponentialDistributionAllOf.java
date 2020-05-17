package tramways.inbound.model;

import java.util.Objects;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import javax.validation.constraints.*;
import io.swagger.annotations.*;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-05-08T22:58:22.447039+02:00[Europe/Berlin]")
public class ExponentialDistributionAllOf   {
  
  private BigDecimal lambda;

  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("lambda")
  public BigDecimal getLambda() {
    return lambda;
  }
  public void setLambda(BigDecimal lambda) {
    this.lambda = lambda;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExponentialDistributionAllOf exponentialDistributionAllOf = (ExponentialDistributionAllOf) o;
    return Objects.equals(lambda, exponentialDistributionAllOf.lambda);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lambda);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExponentialDistributionAllOf {\n");
    
    sb.append("    lambda: ").append(toIndentedString(lambda)).append("\n");
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

