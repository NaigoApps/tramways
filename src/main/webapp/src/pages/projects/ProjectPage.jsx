import React from "react";
import useRemote from "../../hooks/useRemote";
import Page from "../Page";
import RoadMapItem from "./RoadMapItem";
import { Grid } from "@material-ui/core";

export default function ProjectPage({ navigate, projectId }) {
  const [project, refresh] = useRemote("projects/" + projectId);

  if (!project) {
    return null;
  }

  return (
    <Page title={`Project ${project.name}`}>
      <Grid container spacing={1}>
        {project.maps
          .sort((m1, m2) => m1.name.localeCompare(m2.name))
          .map(map => (
            <Grid item xs={4}>
              <RoadMapItem
                project={project}
                map={map}
                refresh={refresh}
                navigate={navigate}
              />
            </Grid>
          ))}
      </Grid>
    </Page>
  );
}
