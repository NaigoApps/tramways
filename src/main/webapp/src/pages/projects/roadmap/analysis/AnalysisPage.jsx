import React, { useState } from "react";
import AnalysisComponent from "./AnalysisComponent";
import Page from "../../../Page";
import useRemote from "../../../../hooks/useRemote";
import { Grid } from "@material-ui/core";
import AnalysisResultDialog from "./AnalysisResultDialog";
import useNetwork from "../../../../hooks/useNetwork";

export default function AnalysisPage({ navigate, projectId, mapId }) {
  const [project, refresh] = useRemote("projects/" + projectId);

  const { remove } = useNetwork();
  async function deleteAnalysis(analysis) {
    const result = await remove(
      `projects/${projectId}/maps/${mapId}/analysis/${analysis.uuid}`
    );
    if (result !== null) {
      refresh();
    }
  }

  const [analysisName, setAnalysisName] = useState(null);
  const [selectedAnalysis, setSelectedAnalysis] = useState(null);

  if (!project) {
    return null;
  }

  const map = project.maps.find(m => m.uuid === mapId);

  console.log(project);
  console.log(map);

  return (
    <Page title={`Analysis of ${map && map.name}`}>
      <Grid container spacing={1}>
        {map.analysis &&
          map.analysis.map(a => (
            <Grid item xs={4}>
              <AnalysisComponent
                analysis={a}
                onSelectAnalysis={res => {
                  setSelectedAnalysis(res);
                  setAnalysisName(a.name);
                }}
                onDeleteAnalysis={() => deleteAnalysis(a)}
              />
            </Grid>
          ))}
      </Grid>
      {selectedAnalysis && (
        <AnalysisResultDialog
          name={analysisName}
          result={selectedAnalysis}
          onClose={() => setSelectedAnalysis(null)}
        />
      )}
    </Page>
  );
}
