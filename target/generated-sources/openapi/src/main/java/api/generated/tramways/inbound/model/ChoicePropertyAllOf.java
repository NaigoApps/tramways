package tramways.inbound.model;

import java.util.Objects;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import tramways.inbound.model.ChoiceElement;
import javax.validation.constraints.*;
import io.swagger.annotations.*;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-09-30T22:40:17.034366700+02:00[Europe/Berlin]")
public class ChoicePropertyAllOf   {
  
  private List<ChoiceElement> choices = new ArrayList<ChoiceElement>();
  private String value;

  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("choices")
  public List<ChoiceElement> getChoices() {
    return choices;
  }
  public void setChoices(List<ChoiceElement> choices) {
    this.choices = choices;
  }

  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("value")
  public String getValue() {
    return value;
  }
  public void setValue(String value) {
    this.value = value;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChoicePropertyAllOf choicePropertyAllOf = (ChoicePropertyAllOf) o;
    return Objects.equals(choices, choicePropertyAllOf.choices) &&
        Objects.equals(value, choicePropertyAllOf.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(choices, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChoicePropertyAllOf {\n");
    
    sb.append("    choices: ").append(toIndentedString(choices)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

