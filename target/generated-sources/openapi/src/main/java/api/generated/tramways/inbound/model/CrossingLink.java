package tramways.inbound.model;

import java.util.Objects;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import tramways.inbound.model.Configurable;
import tramways.inbound.model.LaneAllOf;
import tramways.inbound.model.Property;
import javax.validation.constraints.*;
import io.swagger.annotations.*;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-04-13T14:51:32.877637400+02:00[Europe/Berlin]")
public class CrossingLink   {
  
  private String id;
  private List<Property> props = new ArrayList<Property>();
  private String sourceId;
  private String destinationId;

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
  @JsonProperty("props")
  public List<Property> getProps() {
    return props;
  }
  public void setProps(List<Property> props) {
    this.props = props;
  }

  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("sourceId")
  public String getSourceId() {
    return sourceId;
  }
  public void setSourceId(String sourceId) {
    this.sourceId = sourceId;
  }

  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("destinationId")
  public String getDestinationId() {
    return destinationId;
  }
  public void setDestinationId(String destinationId) {
    this.destinationId = destinationId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CrossingLink crossingLink = (CrossingLink) o;
    return Objects.equals(id, crossingLink.id) &&
        Objects.equals(props, crossingLink.props) &&
        Objects.equals(sourceId, crossingLink.sourceId) &&
        Objects.equals(destinationId, crossingLink.destinationId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, props, sourceId, destinationId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CrossingLink {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    props: ").append(toIndentedString(props)).append("\n");
    sb.append("    sourceId: ").append(toIndentedString(sourceId)).append("\n");
    sb.append("    destinationId: ").append(toIndentedString(destinationId)).append("\n");
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

