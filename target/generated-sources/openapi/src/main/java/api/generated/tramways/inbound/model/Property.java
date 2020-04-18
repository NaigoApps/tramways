package tramways.inbound.model;

import java.util.Objects;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import tramways.inbound.model.PropertyType;
import javax.validation.constraints.*;
import io.swagger.annotations.*;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-04-13T17:27:03.760042100+02:00[Europe/Berlin]")@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "propertyType", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = IntegerProperty.class, name = "IntegerProperty"),
  @JsonSubTypes.Type(value = StringProperty.class, name = "StringProperty"),
  @JsonSubTypes.Type(value = DecimalProperty.class, name = "DecimalProperty"),
  @JsonSubTypes.Type(value = DistributionProperty.class, name = "DistributionProperty"),
})

public class Property   {
  
  private String name;
  private PropertyType propertyType;

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
  
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("propertyType")
  @NotNull
  public PropertyType getPropertyType() {
    return propertyType;
  }
  public void setPropertyType(PropertyType propertyType) {
    this.propertyType = propertyType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Property property = (Property) o;
    return Objects.equals(name, property.name) &&
        Objects.equals(propertyType, property.propertyType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, propertyType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Property {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    propertyType: ").append(toIndentedString(propertyType)).append("\n");
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

