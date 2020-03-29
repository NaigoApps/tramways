import React from "react";
import IntegerPropertyInput from "./IntegerPropertyInput";
import StringPropertyInput from "./StringPropertyInput";
import DecimalPropertyInput from "./DecimalPropertyInput";
import ChoicePropertyInput from "./ChoicePropertyInput";

export default function PropertyInput({ name, type, value, onChange }) {
  const componentMap = {
    integer: IntegerPropertyInput,
    string: StringPropertyInput,
    decimal: DecimalPropertyInput,
    choice: ChoicePropertyInput
  };

  let Component = componentMap[type];
  if (!Component) {
    Component = () => <p>Unsupported</p>;
  }
  return <Component label={name} value={value} onChange={onChange} />;
}
