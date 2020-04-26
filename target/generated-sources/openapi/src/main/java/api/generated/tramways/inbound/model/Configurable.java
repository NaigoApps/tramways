package tramways.inbound.model;

import java.util.Objects;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import tramways.inbound.model.Property;
import javax.validation.constraints.*;
import io.swagger.annotations.*;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-04-26T14:37:23.351990800+02:00[Europe/Berlin]")@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "configurableType", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = RelevantPoint.class, name = "RelevantPoint"),
  @JsonSubTypes.Type(value = Lane.class, name = "Lane"),
  @JsonSubTypes.Type(value = CrossingLink.class, name = "CrossingLink"),
})

public class Configurable   {
  
  private String id;
  private String configurableId;
  private String configurableType;
  private List<Property> props = new ArrayList<Property>();

  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("id")
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("configurableId")
  public String getConfigurableId() {
    return configurableId;
  }
  public void setConfigurableId(String configurableId) {
    this.configurableId = configurableId;
  }

  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("configurableType")
  public String getConfigurableType() {
    return configurableType;
  }
  public void setConfigurableType(String configurableType) {
    this.configurableType = configurableType;
  }

  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("props")
  public List<Property> getProps() {
    return props;
  }
  public void setProps(List<Property> props) {
    this.props = props;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Configurable configurable = (Configurable) o;
    return Objects.equals(id, configurable.id) &&
        Objects.equals(configurableId, configurable.configurableId) &&
        Objects.equals(configurableType, configurable.configurableType) &&
        Objects.equals(props, configurable.props);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, configurableId, configurableType, props);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Configurable {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    configurableId: ").append(toIndentedString(configurableId)).append("\n");
    sb.append("    configurableType: ").append(toIndentedString(configurableType)).append("\n");
    sb.append("    props: ").append(toIndentedString(props)).append("\n");
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

