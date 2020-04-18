package tramways.inbound.model;

import java.util.Objects;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import tramways.inbound.model.Distribution;
import tramways.inbound.model.DistributionType;
import tramways.inbound.model.UniformDistributionAllOf;
import javax.validation.constraints.*;
import io.swagger.annotations.*;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-04-13T17:27:03.760042100+02:00[Europe/Berlin]")
public class UniformDistribution extends Distribution  {
  
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
    UniformDistribution uniformDistribution = (UniformDistribution) o;
    return Objects.equals(left, uniformDistribution.left) &&
        Objects.equals(right, uniformDistribution.right);
  }

  @Override
  public int hashCode() {
    return Objects.hash(left, right);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UniformDistribution {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
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

