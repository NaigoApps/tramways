import { TextField } from "@material-ui/core";
import React, { useState } from "react";
import useNetwork from "../hooks/useNetwork";
import OkCancelDialog from "../widgets/OkCancelDialog";

export default function ProjectEditor({ project, onAbort, onConfirm }) {
  const [newName, setNewName] = useState(project.name);

  const { put } = useNetwork();

  async function updateProject() {
    const result = await put("projects/" + project.uuid, { name: newName });
    if (result != null) {
      onConfirm();
    }
  }

  return (
    <OkCancelDialog onOk={updateProject} onCancel={onAbort} visible>
      <TextField
        label="Project name"
        onChange={evt => setNewName(evt.target.value)}
        value={newName}
      />
    </OkCancelDialog>
  );
}
