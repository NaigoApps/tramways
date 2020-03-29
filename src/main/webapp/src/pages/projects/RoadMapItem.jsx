import {
  Button,
  Card,
  CardActions,
  CardContent,
  TextField,
  Typography
} from "@material-ui/core";
import React, { useEffect, useRef, useState } from "react";
import useNetwork from "../../hooks/useNetwork";
import useStyles from "../../utils/styles";
import OkCancelDialog from "../../widgets/OkCancelDialog";

export default function RoadMapItem({ project, map, refresh, navigate }) {
  const { remove, post } = useNetwork();
  const classes = useStyles();

  const cloneMapNameInput = useRef(null);

  function openMap() {
    navigate(`./${map.uuid}`);
  }

  const [isShowCloneDialog, setShowCloneDialog] = useState(false);
  const [cloneMapName, setCloneMapName] = useState("");

  const showCloneDialog = () => {
    setShowCloneDialog(true);
    setCloneMapName(map.name);
  };

  const hideCloneDialog = () => setShowCloneDialog(false);

  useEffect(() => {
    if (isShowCloneDialog && cloneMapNameInput.current) {
      cloneMapNameInput.current.focus();
    }
  }, [isShowCloneDialog]);

  async function deleteMap() {
    await remove("projects/" + project.uuid + "/maps/" + map.uuid);
    refresh();
  }

  async function cloneMap() {
    const result = await post(
      `projects/${project.uuid}/maps/${map.uuid}/clone`,
      { value: cloneMapName }
    );
    if (result !== null) {
      hideCloneDialog();
      setCloneMapName("");
      refresh();
    }
  }

  return (
    <Card className={classes.card}>
      <CardContent>
        <Typography variant="h5">{map.name}</Typography>
        {/* <Typography variant="body1">{`Entrances: ${map.stats.sources}`}</Typography>
        <Typography variant="body1">{`Crossings: ${map.stats.crossings}`}</Typography>
        <Typography variant="body1">{`Exits: ${map.stats.segments}`}</Typography> */}
      </CardContent>
      <CardActions>
        <Button variant="contained" color="primary" onClick={() => openMap()}>
          Open
        </Button>
        <Button variant="outlined" color="primary" onClick={showCloneDialog}>
          Duplicate
        </Button>
        <Button onClick={() => deleteMap()}>Delete</Button>
      </CardActions>
      <OkCancelDialog
        visible={isShowCloneDialog}
        onCancel={hideCloneDialog}
        onOk={cloneMap}
      >
        <TextField
          variant="outlined"
          ref={cloneMapNameInput}
          label="New map name"
          value={cloneMapName}
          onChange={evt => setCloneMapName(evt.target.value)}
        />
      </OkCancelDialog>
    </Card>
  );
}
