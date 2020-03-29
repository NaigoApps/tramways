import {
  Button,
  Card,
  CardActions,
  CardContent,
  Fab,
  Grid,
  Typography
} from "@material-ui/core";
import AddIcon from "@material-ui/icons/Add";
import React, { useState } from "react";
import ProjectEditor from "../../components/ProjectEditor";
import useNetwork from "../../hooks/useNetwork";
import useRemote from "../../hooks/useRemote";
import useStyles from "../../utils/styles";
import Page from "../Page";

export default function ProjectsPage({ navigate }) {
  const { remove } = useNetwork();
  const classes = useStyles();

  const [projects, refresh] = useRemote("projects");
  const [selectedProject, setSelectedProject] = useState(null);

  function openProject(project) {
    navigate("/projects/" + project.uuid);
  }

  function editProject(project) {
    setSelectedProject(project);
  }

  async function deleteProject(project) {
    await remove("projects/" + project.uuid);
    refresh();
  }

  if (!projects) {
    return null;
  }

  return (
    <Page title="Projects">
      <Fab
        className={classes.fab}
        color="primary"
        onClick={() => navigate("/projects/new")}
      >
        <AddIcon />
      </Fab>
      <Grid container spacing={1}>
        {projects.map(project => (
          <Grid item xs={4}>
            <Card className={classes.card}>
              <CardContent>
                <Typography variant="h5">{project.name}</Typography>
                {project.maps
                  .sort((m1, m2) => m1.name.localeCompare(m2.name))
                  .map(map => (
                    <Typography variant="h6">{map.name}</Typography>
                  ))}
              </CardContent>
              <CardActions>
                <Button
                  variant="contained"
                  color="primary"
                  onClick={() => openProject(project)}
                >
                  Open
                </Button>
                <Button color="primary" onClick={() => editProject(project)}>
                  Edit
                </Button>
                <Button onClick={() => deleteProject(project)}>Delete</Button>
              </CardActions>
            </Card>
          </Grid>
        ))}
      </Grid>
      {selectedProject && (
        <ProjectEditor
          project={selectedProject}
          onConfirm={() => {
            setSelectedProject(null);
            refresh();
          }}
          onAbort={() => setSelectedProject(null)}
        />
      )}
    </Page>
  );
}
