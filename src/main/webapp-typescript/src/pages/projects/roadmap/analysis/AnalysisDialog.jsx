import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  TextField
} from "@material-ui/core";
import CheckIcon from "@material-ui/icons/Check";
import React, { useState } from "react";
import useNetwork from "../../../../hooks/useNetwork";
import useRemote from "../../../../hooks/useRemote";
import useStates from "../../../../hooks/useStates";
import PropertyInput from "../../../properties/PropertyInput";

export default function AnalysisDialog({
  visible,
  onClose,
  analysis,
  projectId,
  mapId
}) {
  const { post } = useNetwork();

  const [options] = useRemote(
    `analysis/${analysis}/params/${projectId}/${mapId}`
  );

  const [name, setName] = useState("");
  const [, getParam, setParam] = useStates();

  async function launchAnalysis() {
    const result = await post(
      `analysis/${analysis}/launch/${projectId}/${mapId}?name=${name}`,
      []
    );
    if (result !== null) {
      onClose();
    }
  }

  if (!options) {
    return null;
  }

  return (
    <Dialog onClose={onClose} open={visible}>
      <DialogTitle>Provide following parameters</DialogTitle>
      <DialogContent>
        <TextField
          label="Name"
          variant="outlined"
          value={name}
          onChange={evt => setName(evt.target.value)}
        />
        {options.map(option => (
          <PropertyInput
            {...option}
            value={getParam(option.name)}
            onChange={value => setParam(option.name, value)}
          />
        ))}
      </DialogContent>
      <DialogActions>
        <Button
          variant="contained"
          color="primary"
          startIcon={<CheckIcon />}
          onClick={launchAnalysis}
        >
          Launch analysis
        </Button>
      </DialogActions>
    </Dialog>
  );
}
