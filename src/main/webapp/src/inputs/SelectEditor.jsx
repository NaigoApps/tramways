import { FormControl, InputLabel, MenuItem, Select } from "@material-ui/core";
import React from "react";

export default function SelectEditor({
  id = v => v,
  text = v => v,
  options,
  value,
  disabled,
  className,
  kind,
  label,
  multiSelect,
  onSelectOption,
  onChange,
  valueId,
  ...others
}) {
  const inputLabel = React.useRef(null);
  const [labelWidth, setLabelWidth] = React.useState(0);
  React.useEffect(() => {
    setLabelWidth(inputLabel.current.offsetWidth);
  }, []);

  return (
    <FormControl variant="outlined" className={className}>
      <InputLabel ref={inputLabel}>{label}</InputLabel>
      <Select
        multiple={multiSelect}
        onChange={evt => onChange(evt.target.value)}
        value={value}
        labelWidth={labelWidth}
      >
        {options.map(option => (
          <MenuItem key={id(option)} value={option}>
            {text(option)}
          </MenuItem>
        ))}
      </Select>
    </FormControl>
  );
}
