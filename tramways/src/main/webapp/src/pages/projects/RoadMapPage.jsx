import React from "react";
import useRemote from "../../hooks/useRemote";
import Page from "../Page";
import RoadMapComponent from "./RoadMapComponent";

export default function RoadMapPage({ navigate, projectId, mapId }) {
  const [project, refresh] = useRemote("projects/" + projectId);

  if (!project) {
    return null;
  }

  const map = project.maps.filter(m => m.uuid === mapId)[0];

  return (
    <Page title={map && map.name}>
      <RoadMapComponent
        refresh={refresh}
        projectId={projectId}
        mapId={mapId}
        map={map}
        navigate={navigate}
      />
    </Page>
  );
}
