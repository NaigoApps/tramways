import {
  Button,
  Dialog,
  DialogActions,
  DialogContent
} from "@material-ui/core";
import React from "react";

export default function Alert({ children, large, visible, onClose }) {
  return (
    <Dialog open={visible} onClose={onClose}>
      <DialogContent>{children}</DialogContent>
      <DialogActions>
        <Button variant="contained" color="secondary" onClick={onClose}>
          Chiudi
        </Button>
      </DialogActions>
    </Dialog>
  );
}
