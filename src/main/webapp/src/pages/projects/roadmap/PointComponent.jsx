import { Box } from "@material-ui/core";
import React from "react";
import DestinationPoint from "./DestinationPoint";
import SourcePoint from "./SourcePoint";
import TrafficLightPoint from "./TrafficLightPoint";

export default function PointComponent({ map, point, onConfigure }) {
  const componentMap = {
    source: SourcePoint,
    destination: DestinationPoint,
    trafficLight: TrafficLightPoint
  };

  console.log(point);

  const Component = componentMap[point.type];
  return (
    <Box m={1}>
      <Component map={map} point={point} onConfigure={onConfigure} />
    </Box>
  );
}
