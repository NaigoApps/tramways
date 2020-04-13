import React from "react";
import TextField from "@material-ui/core/TextField";
import useStyles from "../../utils/useStyles";

export default function DecimalPropertyInput({ label, value, onChange }) {
  const classes = useStyles();
  return (
    <TextField
      className={classes.formControl}
      label={label || "Value"}
      variant="outlined"
      value={value}
      onChange={evt => onChange(evt.target.value)}
    />
  );
}
