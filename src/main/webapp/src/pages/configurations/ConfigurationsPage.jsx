import React from "react";
import useRemote from "../../hooks/useRemote";
import Page from "../Page";
import useNetwork from "../../hooks/useNetwork";
import {
  Typography,
  Button,
  Fab,
  Grid,
  Card,
  CardContent,
  CardActions
} from "@material-ui/core";
import AddIcon from "@material-ui/icons/Add";
import useStyles from "../../utils/useStyles";

export default function ConfigurationsPage({ navigate }) {
  const { remove } = useNetwork();
  const classes = useStyles();

  const [confs, refresh] = useRemote("configurations");

  function editConfiguration(conf) {}

  async function deleteConfiguration(conf) {
    await remove("configurations/" + conf.uuid);
    refresh();
  }

  if (!confs) {
    return null;
  }

  return (
    <Page title="Configurations">
      <Fab
        color="primary"
        aria-label="add"
        className={classes.fab}
        onClick={() => navigate("./new")}
      >
        <AddIcon />
      </Fab>
      <Grid container spacing={1}>
        {confs.map(conf => (
          <Grid item xs={4}>
            <Card className={classes.card}>
              <CardContent>
                <Typography variant="h5">{conf.name}</Typography>
                {conf.properties.map(property => (
                  <Typography variant="h6">
                    {`${property.name}: ${property.value}`}
                  </Typography>
                ))}
              </CardContent>
              <CardActions>
                <Button size="small" onClick={() => editConfiguration(conf)}>
                  Edit
                </Button>
                <Button size="small" onClick={() => deleteConfiguration(conf)}>
                  Delete
                </Button>
              </CardActions>
            </Card>
          </Grid>
        ))}
      </Grid>
    </Page>
  );
}
