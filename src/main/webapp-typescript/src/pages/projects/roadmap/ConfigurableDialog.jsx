import { Button, Dialog, DialogActions, DialogTitle } from "@material-ui/core";
import CheckIcon from "@material-ui/icons/Check";
import React from "react";
import useStates from "../../../hooks/useStates";
import PropertyInput from "../../properties/PropertyInput";

export default function ConfigurableDialog({
  visible,
  onClose,
  configurable,
  properties = [],
  onConfirm
}) {
  const initialProps = {};
  properties.forEach(p => (initialProps[p.name] = { ...p }));

  const [props, , setProp] = useStates(initialProps);

  return (
    <Dialog onClose={onClose} open={visible}>
      <DialogTitle>{`Properties for ${configurable}`}</DialogTitle>
      {Object.entries(props).map(([n, p]) => {
        return (
          <PropertyInput
            {...p}
            onChange={value => setProp(p.name, { ...p, value })}
          />
        );
      })}
      <DialogActions>
        <Button
          variant="contained"
          color="primary"
          startIcon={<CheckIcon />}
          onClick={() =>
            onConfirm(
              Object.entries(props).map(([k, v]) => ({
                ...v
              }))
            )
          }
        >
          Confirm
        </Button>
      </DialogActions>
    </Dialog>
  );
}
