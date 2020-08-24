package tramways.inbound.model;

import java.util.Objects;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import tramways.inbound.model.DistributionType;
import javax.validation.constraints.*;
import io.swagger.annotations.*;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-08-21T17:17:38.852617+02:00[Europe/Rome]")@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "distributionType", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = UniformDistribution.class, name = "UNIFORM"),
  @JsonSubTypes.Type(value = ExponentialDistribution.class, name = "EXPONENTIAL"),
})

public class Distribution   {
  
  private DistributionType distributionType;

  /**
   **/
  
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("distributionType")
  @NotNull
  public DistributionType getDistributionType() {
    return distributionType;
  }
  public void setDistributionType(DistributionType distributionType) {
    this.distributionType = distributionType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Distribution distribution = (Distribution) o;
    return Objects.equals(distributionType, distribution.distributionType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(distributionType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Distribution {\n");
    
    sb.append("    distributionType: ").append(toIndentedString(distributionType)).append("\n");
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

