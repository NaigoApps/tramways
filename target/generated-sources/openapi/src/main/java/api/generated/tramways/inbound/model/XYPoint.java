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

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-08-19T18:54:09.902175+02:00[Europe/Rome]")
public class XYPoint   {
  
  private BigDecimal x;
  private BigDecimal y;

  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("x")
  public BigDecimal getX() {
    return x;
  }
  public void setX(BigDecimal x) {
    this.x = x;
  }

  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("y")
  public BigDecimal getY() {
    return y;
  }
  public void setY(BigDecimal y) {
    this.y = y;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    XYPoint xyPoint = (XYPoint) o;
    return Objects.equals(x, xyPoint.x) &&
        Objects.equals(y, xyPoint.y);
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class XYPoint {\n");
    
    sb.append("    x: ").append(toIndentedString(x)).append("\n");
    sb.append("    y: ").append(toIndentedString(y)).append("\n");
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

