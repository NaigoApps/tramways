import { Select } from "@material-ui/core";
import React from "react";

export default function ChoicePropertyInput({ label, value, onChange }) {
  return (
    <Select
      label={label || "Value"}
      variant="outlined"
      value={value}
      onChange={evt => onChange(evt.target.value)}
    />
  );
}
