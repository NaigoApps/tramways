import {
  Button,
  Dialog,
  DialogActions,
  DialogContent
} from "@material-ui/core";
import { JsonEditor } from "jsoneditor-react";
import "jsoneditor-react/es/editor.min.css";
import React, { useState } from "react";
import Alert from "./Alert";

export default function JSONComponent({
  initialJSON,
  onChange,
  onClose,
  visible
}) {
  const [options, setOptions] = useState({
    mode: "code"
  });

  const [json, setJson] = useState(initialJSON);

  const [error, setError] = useState(null);

  return (
    <Dialog
      onEntered={() => setOptions({ mode: "code" })}
      onClose={onClose}
      open={visible}
      fullScreen
    >
      <DialogContent className>
        <JsonEditor
          htmlElementProps={{ style: { height: "100%" } }}
          {...options}
          value={json}
          onChange={setJson}
        />
      </DialogContent>
      <DialogActions>
        <Button
          variant="contained"
          color="primary"
          onClick={() => {
            try {
              onChange(json);
            } catch (err) {
              setError("Map not valid");
            }
          }}
        >
          Save
        </Button>
        <Button onClick={onClose}>Close</Button>
      </DialogActions>
      <Alert onClose={() => setError(null)} visible={!!error}>
        <p>{error}</p>
      </Alert>
    </Dialog>
  );
}
