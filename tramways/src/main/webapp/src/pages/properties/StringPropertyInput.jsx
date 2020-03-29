import { TextField } from "@material-ui/core";
import React from "react";
import useStyles from "../../utils/styles";

export default function StringPropertyInput({ label, value, onChange }) {
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
