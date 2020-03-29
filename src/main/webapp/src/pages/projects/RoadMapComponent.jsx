import { Box, Button, Grid } from "@material-ui/core";
import React, { Fragment, useState } from "react";
import useNetwork from "../../hooks/useNetwork";
import JSONComponent from "../../widgets/JSONComponent";
import AnalysisDialog from "./roadmap/analysis/AnalysisDialog";
import AvailableAnalysisDialog from "./roadmap/analysis/AvailableAnalysisDialog";
import ConfigurableDialog from "./roadmap/ConfigurableDialog";
import RoadMap from "./roadmap/RoadMap";
import TrafficLightPoint from "./roadmap/TrafficLightPoint";

export default function RoadMapComponent({
  projectId,
  mapId,
  map,
  refresh,
  navigate
}) {
  const { put } = useNetwork();
  const [showEditor, setShowEditor] = useState(false);
  const [showAnalysisDialog, setShowAnalysisDialog] = useState(false);
  const [chosenAnalysis, setChosenAnalysis] = useState(null);

  const [configurable, setConfigurable] = useState(null);

  const smartMap = new RoadMap(map, editMap);

  async function editMap(json) {
    const result = await put(
      `projects/${projectId}/maps/${mapId}`,
      JSON.stringify(json)
    );
    if (result !== null) {
      setShowEditor(false);
      refresh();
    }
  }

  return (
    <Fragment>
      <Box m={2}>
        <Grid container spacing={1}>
          <Grid item>
            <Button
              variant="contained"
              color="primary"
              onClick={() => setShowAnalysisDialog(true)}
            >
              Analyze
            </Button>
          </Grid>
          <Grid item>
            <Button
              variant="outlined"
              color="primary"
              onClick={() => navigate("./analysis")}
            >
              Analysis
            </Button>
          </Grid>
          <Grid item>
            <Button
              variant="outlined"
              color="primary"
              onClick={() => setShowEditor(true)}
            >
              Edit
            </Button>
          </Grid>
        </Grid>
      </Box>
      <Box m={2}>
        {smartMap.sources.map(point => (
          <TrafficLightPoint
            key={point.uuid}
            map={smartMap}
            point={point}
            onConfigure={setConfigurable}
          />
        ))}
      </Box>
      <Box m={2}>
        {smartMap.crossingPoints.map(point => (
          <TrafficLightPoint
            key={point.uuid}
            map={smartMap}
            point={point}
            onConfigure={setConfigurable}
          />
        ))}
      </Box>
      <Box m={2}>
        {smartMap.destinations.map(point => (
          <TrafficLightPoint
            key={point.uuid}
            map={smartMap}
            point={point}
            onConfigure={setConfigurable}
          />
        ))}
      </Box>
      <JSONComponent
        onClose={() => setShowEditor(false)}
        visible={showEditor}
        initialJSON={{
          points: map.points,
          additionalProperties: map.additionalProperties
        }}
        onChange={editMap}
      />
      <AvailableAnalysisDialog
        onClose={() => setShowAnalysisDialog(false)}
        onChooseAnalysis={setChosenAnalysis}
        visible={showAnalysisDialog}
      />
      {chosenAnalysis && (
        <AnalysisDialog
          visible={chosenAnalysis}
          projectId={projectId}
          mapId={mapId}
          analysis={chosenAnalysis}
          onClose={() => setChosenAnalysis(null)}
        />
      )}
      {configurable && (
        <ConfigurableDialog
          visible={!!configurable}
          onClose={() => setConfigurable(null)}
          configurable={configurable}
          properties={smartMap.properties[configurable]}
          onConfirm={props => {
            console.log(props);
            smartMap.addProperties(configurable, props);
            smartMap.sendUpdates();
          }}
        />
      )}
    </Fragment>
  );
}
