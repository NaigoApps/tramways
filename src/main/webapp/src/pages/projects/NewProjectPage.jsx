import { Button, FormControl } from "@material-ui/core";
import TextField from "@material-ui/core/TextField";
import React, { useState } from "react";
import useNetwork from "../../hooks/useNetwork";
import InputTextFile from "../../inputs/InputTextFile";
import Page from "../Page";
import useStyles from "../../utils/useStyles";
import CheckIcon from "@material-ui/icons/Check";

export default function NewProjectPage({ navigate }) {
  const { post } = useNetwork();

  const classes = useStyles();

  const [name, setName] = useState("");
  const [mapName, setMapName] = useState("");
  const [map, setMap] = useState("");

  async function createProject() {
    const parsedMap = JSON.parse(map);
    const result = await post("projects", {
      name,
      maps: [
        {
          name: mapName,
          points: parsedMap.points
        }
      ]
    });
    if (result) {
      navigate("./../" + result);
    }
  }

  return (
    <Page title="New project">
      <div>
        <TextField
          className={classes.formControl}
          variant="outlined"
          label="Project name"
          value={name}
          onChange={evt => setName(evt.target.value)}
        />
      </div>
      <div>
        <TextField
          label="Map name"
          className={classes.formControl}
          variant="outlined"
          value={mapName}
          onChange={evt => setMapName(evt.target.value)}
        />
      </div>
      <div>
        <InputTextFile
          variant="outlined"
          label="Load a map"
          onChange={setMap}
        />
      </div>
      <div>
        <FormControl className={classes.formControl}>
          <Button
            startIcon={<CheckIcon />}
            color="primary"
            variant="contained"
            onClick={createProject}
          >
            Confirm
          </Button>
        </FormControl>
      </div>
    </Page>
  );
}
