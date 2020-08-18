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

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-08-02T15:31:12.408197+02:00[Europe/Rome]")
public class UniformDistributionAllOf   {
  
  private BigDecimal left;
  private BigDecimal right;

  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("left")
  public BigDecimal getLeft() {
    return left;
  }
  public void setLeft(BigDecimal left) {
    this.left = left;
  }

  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("right")
  public BigDecimal getRight() {
    return right;
  }
  public void setRight(BigDecimal right) {
    this.right = right;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UniformDistributionAllOf uniformDistributionAllOf = (UniformDistributionAllOf) o;
    return Objects.equals(left, uniformDistributionAllOf.left) &&
        Objects.equals(right, uniformDistributionAllOf.right);
  }

  @Override
  public int hashCode() {
    return Objects.hash(left, right);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UniformDistributionAllOf {\n");
    
    sb.append("    left: ").append(toIndentedString(left)).append("\n");
    sb.append("    right: ").append(toIndentedString(right)).append("\n");
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

