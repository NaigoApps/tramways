import { Avatar, Card, CardContent, CardHeader } from "@material-ui/core";
import TrafficIcon from "@material-ui/icons/Traffic";
import React from "react";
import CrossingPointConstraint from "./CrossingPointConstraint";
import SourcePoint from "./SourcePoint";
import DestinationPoint from "./DestinationPoint";

export default function TrafficLightPoint({ map, point, onConfigure }) {
  function renderConstraints(constraints) {
    const result = [];
    Object.entries(constraints).forEach(([key, value]) => {
      result.push(
        <CrossingPointConstraint
          key={key}
          point={point}
          map={map}
          source={key}
          destinations={value}
          onConfigure={onConfigure}
        />
      );
    });
    return result;
  }
  function renderTrafficLights(lights) {
    const result = [];
    Object.entries(lights).forEach(([key, value]) => {
      result.push(
        <div key={key} className="columns">
          <div className="column">
            <p>{`Blocking traffic light for ${key}`}</p>
          </div>
        </div>
      );
    });
    return result;
  }

  if (point.sources.length === 0) {
    return <SourcePoint map={map} point={point} onConfigure={onConfigure} />;
  }

  if (point.destinations.length === 0) {
    return (
      <DestinationPoint map={map} point={point} onConfigure={onConfigure} />
    );
  }

  return (
    <Card>
      <CardHeader
        avatar={
          <Avatar>
            <TrafficIcon />
          </Avatar>
        }
        title="Crossing point"
      />
      <CardContent>
        {renderConstraints(point.constraints)}
        {renderTrafficLights(point.trafficLights)}
      </CardContent>
    </Card>
  );
}
