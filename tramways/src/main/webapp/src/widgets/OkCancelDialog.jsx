import {
  Dialog,
  DialogActions,
  DialogContent,
  Button
} from "@material-ui/core";
import React from "react";

export default function OkCancelDialog({
  children,
  large,
  visible,
  onCancel,
  onOk,
  cancelText,
  okText
}) {
  return (
    <Dialog open={visible} onClose={onCancel}>
      <DialogContent>{children}</DialogContent>
      <DialogActions>
        <Button color="primary" onClick={onCancel}>
          {cancelText || "Cancel"}
        </Button>
        <Button variant="contained" color="primary" onClick={onOk}>
          {okText || "Ok"}
        </Button>
      </DialogActions>
    </Dialog>
  );
}
